package net.fashiongo.webadmin.config.security;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.dao.primary.SecurityAccessCodeRepository;
import net.fashiongo.webadmin.dao.primary.SecurityListIPRepository;
import net.fashiongo.webadmin.dao.primary.SecurityLoginControlRepository;
import net.fashiongo.webadmin.dao.primary.SecurityLoginLogRepository;
import net.fashiongo.webadmin.dao.primary.SecurityUserRepository;
import net.fashiongo.webadmin.model.pojo.admin.MenuDS;
import net.fashiongo.webadmin.model.pojo.admin.MenuPermission;
import net.fashiongo.webadmin.model.pojo.admin.SubMenu;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.response.AuthuserResponse;
import net.fashiongo.webadmin.model.primary.SecurityAccessCode;
import net.fashiongo.webadmin.model.primary.SecurityLoginControl;
import net.fashiongo.webadmin.model.primary.SecurityLoginLog;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;

@Component
public class WebadminAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient client;
	
	@Autowired
	private SecurityUserRepository securityUserRepository;
	
	@Autowired
	private SecurityAccessCodeRepository securityAccessCodeRepository;
	
	@Autowired
	private SecurityLoginControlRepository securityLoginControlRepository;
	
	@Autowired
	private SecurityListIPRepository securityListIPRepository;
	
	@Autowired
	private SecurityLoginLogRepository securityLoginLogRepository;
	
	@Autowired
	protected JdbcHelper jdbcHelper;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		AccountCredentials credentials = (AccountCredentials) authentication.getCredentials();
		String username = authentication.getName();
		String password = credentials.getPwd();
		String accessCode = credentials.getAccesscode();
		
		HttpServletRequest request = (HttpServletRequest) authentication.getDetails();
		Integer Loginable = 2;

		JsonResponse response = client.post("/membership/authenticate/3",
				"{\"userName\":\"" + username + "\", \"password\":\"" + password + "\"}");
		if (!response.isSuccess()) {
			this.ResponseException(Loginable, "The UserName and Password combination is invalid");
		}
		this.validateAuthResponse(Loginable, response);
		SecurityUser securityUser = securityUserRepository.findFirstByUserName(username);
		this.checkUserInfo(Loginable, securityUser);
		Loginable = this.checkUserRole(securityUser, accessCode, request);
		
		if(!Loginable.equals(1)) {
			this.ResponseException(Loginable, "Sorry, this account is inactive or not approved yet!");
		}
		
		WebAdminLoginUser webAdminLoginUser = new WebAdminLoginUser();
		webAdminLoginUser.setRoleid(securityUser.getRole());
		webAdminLoginUser.setUserId(securityUser.getUserID());
		webAdminLoginUser.setFullname(securityUser.getFullName());
		webAdminLoginUser.setUsername(securityUser.getUserName());
		webAdminLoginUser.setIpaddr(Utility.getIpAddress(request));
		webAdminLoginUser.setUseragent(Utility.getUserAgent(request));
		
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("WEB_ADMIN"));
		
		WebAdminUserAuthenticationToken auth = new WebAdminUserAuthenticationToken(authentication.getName(),
				authentication.getCredentials(), grantedAuths);

		auth.setUserInfo(webAdminLoginUser);
		auth.setMenuDs(this.getMenuDS(securityUser.getUserID()));
		this.setLoginLog(webAdminLoginUser);
		
		return auth;
	}
	
	private void setLoginLog(WebAdminLoginUser webAdminLoginUser) {
		SecurityLoginLog loginLog = new SecurityLoginLog();
		loginLog.setUserID(webAdminLoginUser.getUserId());
		loginLog.setIp(webAdminLoginUser.getIpaddr());
		loginLog.setLoginOn(LocalDateTime.now());
		
		this.securityLoginLogRepository.save(loginLog);
	}
	
	private void ResponseException(Integer code, String message){
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
		AuthuserResponse result = new AuthuserResponse();
		
		JsonResponse<AuthuserResponse> res = new JsonResponse<AuthuserResponse>();
		res.setSuccess(true);
		result.setUserID(0);
		result.setsCodeNo(code);
		result.setsCodeYn(false);
		result.setMessage(message);
		res.setData(result);
		try {
			ObjectMapper om = new ObjectMapper();
			String returnStr = om.writeValueAsString(res);
			OutputStream ostr = response.getOutputStream();
			ostr.write(returnStr.getBytes());
			ostr.flush();
			ostr.close();
		} catch(Exception e) {
			
		}
	}
	
	private MenuDS getMenuDS(Integer userId) {
		MenuDS menuDs = new MenuDS();
		String spName = "up_wa_Security_GetLeftMenuByUser";
        List<Object> params = new ArrayList<Object>();
        
        params.add(userId);
        List<Object> _result = jdbcHelper.executeSP(spName, params, SubMenu.class, MenuPermission.class);
        
        menuDs.setSubMenus((List<SubMenu>)_result.get(0));
        menuDs.setMenuPermission((List<MenuPermission>)_result.get(1));
        
        return menuDs;
	}
	
	private void checkUserInfo(Integer loginable, SecurityUser securityUser) {
		
		
		if(securityUser == null) {
			this.ResponseException(loginable,"The userName and Password combination is invalid.");
		} else if(securityUser.getActive() == false){
			this.ResponseException(loginable,"Sorry, this account is inactive or not approved yet!");
		}
	}
	
	private Integer checkUserRole(SecurityUser securityUser, String accessCode, HttpServletRequest request) {
		Integer Loginable = 2;
		
		if(!securityUser.getRole().equals("S")) {
			if(!securityUser.getIpTimeExempt()) {
				if(checkIPAddress(request)) {
					boolean bAccessabletime = false;
					Integer weekday = LocalDate.now().getDayOfWeek().getValue() + 1;
					List<SecurityLoginControl> list = securityLoginControlRepository.findByUserIDAndWeekday(securityUser.getUserID(), weekday);
					
					if(!CollectionUtils.isEmpty(list)) {
						String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
						bAccessabletime = ((list.get(0).getTimeFromTime().compareTo(currentTime)<=0) && (list.get(0).getTimeToTime().compareTo(currentTime)>=0));
					}
					
					if(!bAccessabletime) {
						this.ResponseException(5,list.get(0).getTimeFromTime().toString() + " asd " + list.get(0).getTimeToTime().toString());
						Loginable = this.checkAccessCode(accessCode);
					}else {
						Loginable = 1;
					}
				}else {
					Loginable = this.checkAccessCode(accessCode);
				}
			} else {
				Loginable = 1;
			}
		} else {
			Loginable = 1;
		}
		
		return Loginable;
	}
	
	private Integer checkAccessCode(String accessCode) {
		SecurityAccessCode acsCode = securityAccessCodeRepository.findFirstByAccessCode(accessCode);
		if(acsCode != null && acsCode.getExpiredOn().after(new Date()) == true) {
			return 1;
		}else {
			return 5;
		}
	}
	
	private boolean checkIPAddress(HttpServletRequest request) {
		return this.securityListIPRepository.existsByIpAddress(Utility.getIpAddress(request));
	}
	
	private void validateAuthResponse(Integer loginable, JsonResponse response) {
		LinkedHashMap<String, Object> d = (LinkedHashMap<String, Object>) response.getData();
		if (!(Boolean) d.get("isAuthenticated")) {
			if ((Boolean) d.get("isBlocked") != null && (Boolean) d.get("isBlocked")) {
				this.ResponseException(loginable,((String) d.get("message")));
			} else {
				this.ResponseException(loginable,((String) d.get("message")));
			}
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}