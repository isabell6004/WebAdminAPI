/**
 * 
 */
package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.parameter.DelVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorNewsResponse;
import net.fashiongo.webadmin.model.primary.VendorNewsDetail;
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
	 * Get Vendor News
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param GetVendorNewsParameter
	 * @return JsonResponse<GetVendorNewsResponse>
	 */
	@RequestMapping(value="getvendornews", method=RequestMethod.POST)
	public JsonResponse<GetVendorNewsResponse> GetVendorNews (@RequestBody GetVendorNewsParameter parameters) {
		GetVendorNewsResponse result = messageService.GetVendorNews(parameters);
		return new JsonResponse<GetVendorNewsResponse>(true, null, 0, result);
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
	@RequestMapping(value="delvendornews", method=RequestMethod.POST)
	public JsonResponse<Integer> DelVendorNews (@RequestBody DelVendorNewsParameter parameters) {
		Integer result = messageService.DelVendorNews(parameters);
		return new JsonResponse<Integer>(true, null, 0, result);
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
	@RequestMapping(value="getvendornewsdetail", method=RequestMethod.POST)
	public JsonResponse<VendorNewsDetail> GetVendorNewsDetail (@RequestBody GetVendorNewsDetailParameter parameters) {
		VendorNewsDetail result = messageService.GetVendorNewsDetail(parameters);
		return new JsonResponse<VendorNewsDetail>(true, null, 0, result);
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
	//@RequestMapping(value="", method=RequestMethod.POST)
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
	//@RequestMapping(value="", method=RequestMethod.POST)
	public void SetVendorNewsInActive () {
		
	}
}