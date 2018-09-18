package net.fashiongo.webadmin.config.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.model.pojo.WebAdminLoginUser;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

@Component
public class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 60 * 60 * 24; // default, 24 hours
	static final String SECRET = "FGWebAdminAPI6301";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	@Autowired
	@Qualifier("webAdminJsonClient")
	private HttpClient httpClientInstance;
	
	private static HttpClient httpClient;
	
	@PostConstruct     
	public void init () {
		httpClient = this.httpClientInstance;
	}

	public static void addAuthentication(HttpServletResponse response, WebAdminLoginUser webAdminLoginUser)
			throws JsonGenerationException, JsonMappingException, IOException {

	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		ObjectMapper mapper = new ObjectMapper();
		JsonResponse result = new JsonResponse();
		
		if (token != null) {
			try {
				JSONObject obj = new JSONObject();
				obj.put("token", token);
				result = httpClient.postObject("Account/TokenCheck", obj);
			} catch(Exception ex) {
				return null;
			}
			
			WebAdminLoginUser webAdminLoginUser  = mapper.convertValue(result.getData(), WebAdminLoginUser.class);
			
			return result != null ? new AuthenticatedUser(webAdminLoginUser.getUsername(), webAdminLoginUser) : null;
		}
		return null;
	}
}