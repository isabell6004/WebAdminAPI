package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameters;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class MessageService extends ApiService {
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param parameters
	 * @return 
	 */
	public GetMessageResponse GetMessage(GetMessageParameters parameters) {
		GetMessageResponse result = new GetMessageResponse();
		String spName = "up_wa_GetAdminMessage";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getPagenum());
        params.add(parameters.getPagesize());
        params.add(parameters.getParent());
        params.add(parameters.getSendertypeid());
        params.add(parameters.getRecipienttypeid());
        params.add(null);
        params.add(null);
        params.add(parameters.getSender());
        params.add(parameters.getTopic());
        params.add(parameters.getSubject());
        params.add(parameters.getPeriod());
        params.add(parameters.getFromdate());
        params.add(parameters.getTodate());
        params.add(parameters.getStatus());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, Message.class);
        
		result.setTotal((List<Total>)_result.get(0));
		result.setMessagelist((List<Message>) _result.get(1));
		
		return result;
	}
}
