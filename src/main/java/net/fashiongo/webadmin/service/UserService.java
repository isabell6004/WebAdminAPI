package net.fashiongo.webadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.model.pojo.ResultCode;
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
	@SuppressWarnings("unchecked")
	public ResultCode ResetPassword(SetModifyPasswordParameter parameters) {
		ObjectMapper mapper = new ObjectMapper();
		ResultCode results = new ResultCode(true, 1, MSG_CHANGE_SUCCESS);
		try {
			String jsonParameters = mapper.writeValueAsString(parameters);
			JsonResponse<String> result = httpClient.post("membership/resetPassword", jsonParameters);
			results.setSuccess(result.isSuccess());
			if(result.getMessage() != "") results.setResultMsg(result.getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return results;
	}
}
