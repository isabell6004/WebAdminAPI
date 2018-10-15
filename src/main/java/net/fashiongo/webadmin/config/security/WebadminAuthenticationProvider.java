package net.fashiongo.webadmin.config.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.dao.primary.SecurityAccessCodeRepository;
import net.fashiongo.webadmin.dao.primary.SecurityListIPRepository;
import net.fashiongo.webadmin.dao.primary.SecurityLoginControlRepository;
import net.fashiongo.webadmin.dao.primary.SecurityUserRepository;
import net.fashiongo.webadmin.model.pojo.WebAdminLoginUser;
import net.fashiongo.webadmin.model.primary.SecurityAccessCode;
import net.fashiongo.webadmin.model.primary.SecurityLoginControl;
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
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		AccountCredentials credentials = (AccountCredentials) authentication.getCredentials();
		String username = authentication.getName();
		String password = credentials.getPwd();
		String accessCode = credentials.getAccesscode();
		
		HttpServletRequest request = (HttpServletRequest) authentication.getDetails();
		Integer Loginable = 2;

//		JsonResponse response = new JsonResponse();
		JsonResponse response = client.post("/membership/authenticate/3",
				"{\"userName\":\"" + username + "\", \"password\":\"" + password + "\"}");
		if (!response.isSuccess()) {
			throw new BadCredentialsException(password);
		}
		this.validateAuthResponse(response);
		
		SecurityUser securityUser = securityUserRepository.findFirstByUserName(username);
		
		if(securityUser == null) {
			throw new BadCredentialsException("The userName and Password combination is invalid.");
		} else if(securityUser.getActive() == false){
			throw new BadCredentialsException("Sorry, this account is inactive or not approved yet!");
		}
		
		if(!securityUser.getRole().equals("S")) {
			if(!securityUser.getIpTimeExempt()) {
				if(checkIPAddress(request)) {
					boolean bAccessabletime = false;
					Integer weekday = LocalDate.now().getDayOfWeek().getValue() + 1;
					List<SecurityLoginControl> list = securityLoginControlRepository.findByUserIDAndWeekday(securityUser.getUserID(), weekday);
					
					if(!CollectionUtils.isEmpty(list)) {
						bAccessabletime = (list.get(0).getTimeFrom().after(new Date()) && list.get(0).getTimeFrom().before(new Date()));
					}
					
					if(!bAccessabletime) {
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
		
		if(!Loginable.equals(1)) {
			throw new BadCredentialsException("");
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
		return auth;
	}
	
	private Integer checkAccessCode(String accessCode) {
		SecurityAccessCode acsCode = securityAccessCodeRepository.findFirstByAccessCode(accessCode);
		if(acsCode != null && acsCode.getExpiredOn().after(new Date()) == true) {
			return 1;
		}else {
			return 6;
		}
	}
	
	private boolean checkIPAddress(HttpServletRequest request) {
		return this.securityListIPRepository.existsByIpAddress(Utility.getIpAddress(request));
	}
	
	private void test() {
		
	}
	
	private void validateAuthResponse(JsonResponse response) {
		LinkedHashMap<String, Object> d = (LinkedHashMap<String, Object>) response.getData();
		if (!(Boolean) d.get("isAuthenticated")) {
			if ((Boolean) d.get("isBlocked") != null && (Boolean) d.get("isBlocked")) {
				throw new VendorBlockedException((String) d.get("message"));
			} else {
				throw new BadCredentialsException((String) d.get("message"));
			}
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}