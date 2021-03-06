package net.fashiongo.webadmin.config.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.exception.TokenInvalidException;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.response.AuthuserResponse;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;

@Slf4j
@Component
public class TokenAuthenticationService {
	static final String SECRET = "fgwav2^^9070";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	static final long EXPIRATIONTIME = 43200000;// default 12 hours
	
	public static void addAuthentication(HttpServletRequest request, HttpServletResponse response,
			WebAdminUserAuthenticationToken authInfo) throws IOException {
		WebAdminLoginUser webAdminLoginUser = authInfo.getUserInfo();
		AuthuserResponse result = new AuthuserResponse();
		
		Algorithm algorithm = Algorithm.HMAC512(SECRET);
	    String token = JWT.create()
	    	.withClaim("roleid", webAdminLoginUser.getRoleid())
	    	.withClaim("userId", webAdminLoginUser.getUserId())
	    	.withClaim("username", webAdminLoginUser.getUsername())
	    	.withClaim("fullname", webAdminLoginUser.getFullname())
	    	.withClaim("ipaddr", webAdminLoginUser.getIpaddr())
	    	.withClaim("useragent", webAdminLoginUser.getUseragent())
	        .withIssuer("WebAdmin")
	        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	        .sign(algorithm);

		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token);

		JsonResponse<AuthuserResponse> res = new JsonResponse<AuthuserResponse>();
		res.setSuccess(true);
		result.setUserID(webAdminLoginUser.getUserId());
		result.setMenuDS(authInfo.getMenuDs());
		result.setFullName(webAdminLoginUser.getFullname());
		result.setRole(webAdminLoginUser.getRoleid());
		result.setsCodeNo(1);
		result.setsCodeYn(true);
		result.setTokenID(token);
		result.setUserName(webAdminLoginUser.getUsername());
		res.setData(result);

		ObjectMapper om = new ObjectMapper();
		String returnStr = om.writeValueAsString(res);

		try {
			OutputStream ostr = response.getOutputStream();
			ostr.write(returnStr.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Token 을 검사하고 인증 정보를 반환한다.
	 * @param request the request
	 * @return 인증정보. Token 정보가 없을 경우 null return.
	 * @throws TokenInvalidException Token 검증 실패 시 발생.
	 */
	@SuppressWarnings("unchecked")
	public static Authentication getAuthentication(HttpServletRequest request) throws TokenInvalidException {
		String token = request.getHeader(HEADER_STRING);
		ObjectMapper mapper = new ObjectMapper();

		if (token != null) {
			JSONObject obj = new JSONObject();
			try {
				Map<String, Claim> claims = JWT.require(Algorithm.HMAC512(SECRET)).build()
						.verify(token.replace(TOKEN_PREFIX, "").replaceAll("\\s+", "")).getClaims();

				obj.put("roleid", claims.get("roleid").asString());
				obj.put("userId", claims.get("userId").asInt());
				obj.put("username", claims.get("username").asString());
				obj.put("fullname", claims.get("fullname").asString());
				obj.put("ipaddr", claims.get("ipaddr").asString());
				obj.put("useragent", claims.get("useragent").asString());
//				obj.put("nbf", claims.get("nbf").asDouble());
//				obj.put("iat", claims.get("iat").asDouble());
//				obj.put("exp", claims.get("exp").asDouble());

				WebAdminLoginUser webAdminLoginUser = mapper.convertValue(obj, WebAdminLoginUser.class);
				validateClientUser(webAdminLoginUser, request);

				return obj.size() != 0 ? new AuthenticatedUser(webAdminLoginUser.getUsername(), webAdminLoginUser)
						: null;

			} catch (Exception ex) {
				throw new TokenInvalidException(ex.getMessage(), ex);
			}
		}
		return null;
	}

	private static void validateClientUser(WebAdminLoginUser webAdminLoginUser, HttpServletRequest request)
			throws TokenInvalidException {
		// check client ip address
		if (StringUtils.isEmpty(webAdminLoginUser.getIpaddr())) {
			throw new TokenInvalidException("Token is invalid! (No IPAddress)");
		} else {
			if (!webAdminLoginUser.getIpaddr().equals(Utility.getIpAddress(request))) {
				log.info("===== Token is invalid! (Invalid IpAddress) =====");
				log.info("webAdminLoginUser.getIpaddr(): {}", webAdminLoginUser.getIpaddr());
				log.info("Utility.getIpAddress(): {}", Utility.getIpAddress(request));
				log.info("=================================================");
				throw new TokenInvalidException("Token is invalid! (Invalid IpAddress)");
			}
		}

		// check client user agent
		if (StringUtils.isEmpty(webAdminLoginUser.getUseragent())) {
			throw new TokenInvalidException("Token is invalid! (No UserAgent)");
		} else {
			if (!webAdminLoginUser.getUseragent().equals(Utility.getUserAgent(request))) {
				throw new TokenInvalidException("Token is invalid! (Invalid UserAgent)");
			}
		}
	}

}