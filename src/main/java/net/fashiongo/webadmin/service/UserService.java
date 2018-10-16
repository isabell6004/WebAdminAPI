package net.fashiongo.webadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.model.pojo.parameter.SetModifyPasswordParameter;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author DAHYE
 *
 */
@Service
public class UserService extends ApiService {
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;
	
	/**
	 * 
	 * Reset Password
	 * @since 2018. 10. 16.
	 * @author Dahye
	 * @param pageName
	 * @return SecurityMenu
	 */
	public JsonResponse ResetPassword(SetModifyPasswordParameter parameters) {
		ObjectMapper mapper = new ObjectMapper();
		JsonResponse result = new JsonResponse(true, null, 0, null);
		try {
			String jsonParameters = mapper.writeValueAsString(parameters);
			result = httpClient.post("membership/resetPassword", jsonParameters);
			result.setMessage(MSG_CHANGE_SUCCESS);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
