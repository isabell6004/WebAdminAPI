package net.fashiongo.webadmin.config.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.model.pojo.WebAdminLoginUser;

@Component
public class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 60 * 60 * 24; // default, 24 hours
	static final String SECRET = "fgwav2^^9070";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	public static void addAuthentication(HttpServletResponse response, WebAdminLoginUser webAdminLoginUser)
			throws JsonGenerationException, JsonMappingException, IOException {
	}

	public static Authentication getAuthentication(HttpServletRequest request) throws IllegalArgumentException, UnsupportedEncodingException {
		String token = request.getHeader(HEADER_STRING);
		ObjectMapper mapper = new ObjectMapper();
		
		if (token != null) {
			JSONObject obj = new JSONObject();
			try {
				Map<String, Claim> claims = JWT.require(Algorithm.HMAC512(SECRET))
	                    .build()
	                    .verify(token.replace(TOKEN_PREFIX, "").replaceAll("\\s+",""))
	                    .getClaims();
				
//				claims.forEach((key, value) -> {
//		            obj.put(key, value.asString());
//		        });
				
				obj.put("roleid", claims.get("roleid").asString());
				obj.put("userId", claims.get("userId").asInt());
				obj.put("username", claims.get("username").asString());
				obj.put("fullname", claims.get("fullname").asString());
				obj.put("ipaddr", claims.get("ipaddr").asString());
				obj.put("useragent", claims.get("useragent").asString());
				obj.put("nbf", claims.get("nbf").asDouble());
				obj.put("iat", claims.get("iat").asDouble());
				obj.put("exp", claims.get("exp").asDouble());
				
				WebAdminLoginUser webAdminLoginUser = mapper.convertValue(obj, WebAdminLoginUser.class);
				
				return obj.size() != 0 ? new AuthenticatedUser(webAdminLoginUser.getUsername(), webAdminLoginUser) : null;
				
			} catch(Exception ex) {
				return null;
			}
		}
		return null;
	}
}