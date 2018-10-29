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
import net.fashiongo.webadmin.model.pojo.parameter.SetVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorNewsResponse;
import net.fashiongo.webadmin.model.primary.VendorNewsView;
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
	public JsonResponse<GetMessageResponse> getMessage(@RequestBody GetMessageParameter parameters) {
		JsonResponse<GetMessageResponse> results = new JsonResponse<GetMessageResponse>(true, null, null);
		
		GetMessageResponse result = messageService.getMessage(parameters);
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
	public JsonResponse<GetVendorNewsResponse> getVendorNews (@RequestBody GetVendorNewsParameter parameters) {
		GetVendorNewsResponse result = messageService.getVendorNews(parameters);
		return new JsonResponse<GetVendorNewsResponse>(true, null, 0, result);
	}
	
	/**
	 * 
	 * DelVendorNews
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param DelVendorNewsParameter
	 * @return JsonResponse<Integer>
	 */
	@RequestMapping(value="delvendornews", method=RequestMethod.POST)
	public JsonResponse<Integer> delVendorNews (@RequestBody DelVendorNewsParameter parameters) {
		Integer result = messageService.delVendorNews(parameters);
		return new JsonResponse<Integer>(true, null, result, null);
	}
	
	/**
	 * 
	 * GetVendorNewsDetail
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param GetVendorNewsDetailParameter
	 * @return JsonResponse<VendorNewsDetail>
	 */
	@RequestMapping(value="getvendornewsdetail", method=RequestMethod.POST)
	public JsonResponse<VendorNewsView> getVendorNewsDetail (@RequestBody GetVendorNewsDetailParameter parameters) {
		VendorNewsView result = messageService.getVendorNewsDetail(parameters);
		return new JsonResponse<VendorNewsView>(true, null, 0, result);
	}
	
	/**
	 * 
	 * SetVendorNews
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param SetVendorNewsParameter
	 * @return 
	 */
	@RequestMapping(value="setvendornews", method=RequestMethod.POST)
	public JsonResponse<Integer> setVendorNews (@RequestBody SetVendorNewsParameter parameters) {
		Integer result = messageService.setVendorNews(parameters.getVendorNews(), parameters.getSelectedVendor());
		return new JsonResponse<Integer>(true, null, result, null);		
	}
	
	/**
	 * 
	 * SetVendorNewsInActive
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param DelVendorNewsParameter
	 * @return JsonResponse<Integer>
	 */
	@RequestMapping(value="setvendornewsinactive", method=RequestMethod.POST)
	public JsonResponse<Integer> setVendorNewsInActive (@RequestBody DelVendorNewsParameter parameters) {
		Integer result = messageService.setVendorNewsInActive(parameters);
		return new JsonResponse<Integer>(true, null, result, null);		
	}
}