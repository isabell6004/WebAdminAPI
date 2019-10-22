/**
 * 
 */
package net.fashiongo.webadmin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.fashiongo.webadmin.data.model.message.GetRetailerRatingParameter;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.message.ResultMessage;
import net.fashiongo.webadmin.model.pojo.message.parameter.*;
import net.fashiongo.webadmin.model.pojo.message.response.*;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetContactUsResponse;
import net.fashiongo.webadmin.model.primary.MessageCategory;
import net.fashiongo.webadmin.model.primary.TblRetailerNews;
import net.fashiongo.webadmin.model.primary.VendorNewsView;
import net.fashiongo.webadmin.service.MessageService;
import net.fashiongo.webadmin.service.renewal.RenewalMessageService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Incheol Jung
 */
@RestController
@RequestMapping(value="/message", produces = "application/json")
public class MessageController {
	
	@Autowired
	MessageService messageService;

	@Autowired
	RenewalMessageService renewalMessageService;

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
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 31.
	 * @author Reo
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value="getretailernews", method=RequestMethod.POST)
	public JsonResponse<GetRetailerNewsResponse> getRetailerNews(@RequestBody GetRetailerNewsParameter parameters) {
		JsonResponse<GetRetailerNewsResponse> results = new JsonResponse<GetRetailerNewsResponse>(true, null, null);
		
		GetRetailerNewsResponse result = messageService.getRetailerNews(parameters);
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 1.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="getretailernewsdetail", method=RequestMethod.POST)
	public JsonResponse<TblRetailerNews> getRetailerNewsDetail(@RequestBody GetRetailerNewsDetailParameter parameters) {
		JsonResponse<TblRetailerNews> results = new JsonResponse<TblRetailerNews>(false, null, 0, null);
		
		TblRetailerNews result = messageService.getRetailerNewsDetail(parameters);
		
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 1.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="setretailernews", method=RequestMethod.POST)
	public JsonResponse<ResultCode> setRetailerNews(@RequestBody SetRetailerNewsParameter parameters) {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		
		ResultCode result = messageService.setRetailerNews(parameters);
		
		results.setData(result);
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 1.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="delretailernews", method=RequestMethod.POST)
	public JsonResponse<ResultCode> delRetailerNews(@RequestBody List<Integer> parameters) {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		
		ResultCode result = messageService.delRetailerNews(parameters);
		
		results.setData(result);
		
		return results;
	}
	
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 16.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="getcontactus", method=RequestMethod.POST)
	public JsonResponse<GetContactUsResponse> getContactUs(@RequestBody GetContactUsParameter parameters) {
		JsonResponse<GetContactUsResponse> results = new JsonResponse<GetContactUsResponse>(true, null, null);
		
		GetContactUsResponse result = messageService.getContactUs(parameters);
		results.setData(result);
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 19.
	 * @author Reo
	 * @param parameters
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="setcontactusreply", method=RequestMethod.POST)
	public JsonResponse<ResultCode> setContactUsReply(@RequestBody SetContactUsReplyParameter parameters) throws JsonProcessingException {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		
		ResultCode result = messageService.setContactUsReply(parameters);
		
		results.setData(result);
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 20.
	 * @author Reo
	 * @return
	 */
	@RequestMapping(value="getmessagecategory", method=RequestMethod.POST)
	public JsonResponse<List<MessageCategory>> getMessageCategory() {
		JsonResponse<List<MessageCategory>> results = new JsonResponse<List<MessageCategory>>();
		List<MessageCategory> result = messageService.getMessageCategory();
		
		results.setData(result);
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 21.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="setmessage", method=RequestMethod.POST)
	public JsonResponse<ResultMessage> setMessage(@RequestBody SetMessageParameter parameters) {
		JsonResponse<ResultMessage> results = new JsonResponse<ResultMessage>(true, null, 0, null);
		
		ResultMessage result = messageService.setMessage(parameters);
		
		results.setData(result);
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 21.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="setmessagereadyn", method=RequestMethod.POST)
	public JsonResponse<ResultCode> setMessageReadYN(@RequestBody SetMessageReadYNParameter parameters) {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);

		ResultCode result = messageService.setMessageReadYN(parameters);
		
		results.setData(result);
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 21.
	 * @author Reo
	 * @param topReferenceID
	 * @return
	 */
	@RequestMapping(value="getmessagereply", method=RequestMethod.GET)
	public JsonResponse<GetMessageReplyResponse> getMessageReply(@RequestParam(value="referenceid") Integer topReferenceID) {
        JsonResponse<GetMessageReplyResponse> results = new JsonResponse<GetMessageReplyResponse>(true, null, null);
		
        GetMessageReplyResponse result = messageService.getMessageReply(topReferenceID);
		results.setData(result);
		
		return results;
	}
	
	/**
	 * getVendorRating
	 * 
	 * @since 2018. 11. 27.
	 * @author dahye
	 * @param GetVendorRatingParameter
	 * @return GetVendorRatingResponse
	 */
	@RequestMapping(value="getvendorrating", method=RequestMethod.POST)
	public JsonResponse<GetVendorRatingResponse> getVendorRating(@RequestBody GetVendorRatingParameter param) {
		GetVendorRatingResponse result = messageService.getVendorRating(param);
		return new JsonResponse<GetVendorRatingResponse>(true, null, 0, result);
	}
	
	/**
	 * getRetailerRating
	 * 
	 * @since 2018. 11. 27.
	 * @author dahye
	 * @param GetVendorRatingParameter
	 * @return 
	 */
	@RequestMapping(value="getretailerrating", method=RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.message.response.GetRetailerRatingResponse> getRetailerRating(@RequestBody GetRetailerRatingParameter param) {
		net.fashiongo.webadmin.data.model.message.response.GetRetailerRatingResponse retailerRating = renewalMessageService.getRetailerRating(param);

		return new JsonResponse<net.fashiongo.webadmin.data.model.message.response.GetRetailerRatingResponse>(true, null, 0, retailerRating);
	}
}
