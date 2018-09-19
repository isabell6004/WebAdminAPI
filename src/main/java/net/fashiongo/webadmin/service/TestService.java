package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.fgem.EmApplicationRepository;
import net.fashiongo.webadmin.model.pojo.MessageList;
import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameters;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class TestService extends ApiService{
	
	@Autowired
	@Qualifier("webAdminJsonClient")
	private HttpClient httpClient;
	
	@Autowired
	EmApplicationRepository emApplicationRepository;
	
	/**
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @return 
	 */
	public String simpleTest() {
		return "testService -> simpleTest";
	}
	
	/**
	 * 
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @return 
	 * Desc :
	 */
	public String executeException() {
		List<String> arr = new ArrayList<String>();
		arr.add("test");
		arr.get(10);
		
		return "testService -> executeException";
	}
	
	/**
	 * 
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param message
	 * @return 
	 * Desc :
	 */
	public String callhttpNetwork(String message) {
		JsonResponse result = httpClient.get("Account/Test");
		System.out.println("result.getMessage()" + result.getMessage());
		
		return "testService -> callhttpNetwork";
	}
	
	/**
	 * 
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param parameters
	 * @return 
	 * Desc :
	 */
	public String callProc(GetMessageParameters parameters) {
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
		
		return "testService -> callProc";
	}
}
