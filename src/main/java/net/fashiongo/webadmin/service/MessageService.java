package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.parameter.GetMessageParameters;
import net.fashiongo.webadmin.model.pojo.MessageList;
import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.response.GetMessageResponse;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class MessageService extends ApiService {
	
	/**
	 * 
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param parameters
	 * @return 
	 * Desc :
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
        params.add(parameters.getSender());
        params.add(parameters.getTopic());
        params.add(parameters.getSubject());
        params.add(parameters.getPeriod());
        params.add(parameters.getFromdate());
        params.add(parameters.getTodate());
        params.add(parameters.getStatus());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, MessageList.class);
        
		result.setTable(((List<Total>)_result.get(0)).get(0));
		result.setTable1((List<MessageList>) _result.get(1));
		
		return result;
	}
}
