/**
 * 
 */
package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.service.MessageService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * @author Incheol Jung
 */
@RestController
@RequestMapping(value="/message", produces = "application/json")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="getmessage", method=RequestMethod.POST)
	public JsonResponse<GetMessageResponse> GetMessage(@RequestBody GetMessageParameter parameters) {
		JsonResponse<GetMessageResponse> results = new JsonResponse<GetMessageResponse>(true, null, null);
		
		GetMessageResponse result = messageService.GetMessage(parameters);
		results.setData(result);
		
		return results;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void GetVendorNews () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void DelVendorNews () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void GetVendorNewsDetail () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void SetVendorNews () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void SetVendorNewsInActive () {
		
	}
}