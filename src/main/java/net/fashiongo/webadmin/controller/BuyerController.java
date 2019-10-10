package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.buyer.*;
import net.fashiongo.webadmin.data.model.buyer.response.*;
import net.fashiongo.webadmin.model.pojo.buyer.parameter.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.primary.RetailerCompany;
import net.fashiongo.webadmin.service.BuyerService;
import net.fashiongo.webadmin.service.UserService;
import net.fashiongo.webadmin.service.renewal.RenewalBuyerService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * @author DAHYE
 *
 */
@RestController
@RequestMapping(value = "/buyer", produces = "application/json")
public class BuyerController {
	@Autowired 
	BuyerService buyerService;

	@Autowired
	private RenewalBuyerService renewalBuyerService;

	@Autowired
	private UserService userService;
	/**
	 * 
	 * Set Modify Password
	 * 
	 * @since 2018. 10. 15.
	 * @author Dahye Jeong
	 * @param userid, newpwd
	 * @return JsonResponse
	 */
	@RequestMapping(value = "setmodifypassword", method = RequestMethod.POST)
	public JsonResponse<String> setModifyPassword(@RequestBody SetModifyPasswordParameter parameters) {
		ResultCode result = userService.resetPassword(parameters);
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 22.
	 * @author Reo
	 * @param companyName
	 * @return
	 */
	@RequestMapping(value="getretailerlistforcompanyname", method=RequestMethod.GET)
	public JsonResponse<List<RetailerCompany>> GetRetailerListForCompanyName(@RequestParam(value="keyword") String companyName) {
		JsonResponse<List<RetailerCompany>> results = new JsonResponse<List<RetailerCompany>>();
		List<RetailerCompany> result = buyerService.GetRetailerListForCompanyName(companyName);
		
		results.setData(result);
		return results;
	}
	
	/**
	 * SetAdminRetailerReadYN
	 * 
	 * @since 2018. 11. 28.
	 * @author Dahye
	 * @param SetAdminRetailerReadYNParameter
	 * @return Integer
	 */
	@RequestMapping(value="setadminretailerreadyn", method=RequestMethod.POST)
	public JsonResponse<String> SetAdminRetailerReadYN(@RequestBody SetAdminRetailerReadYNParameter param) {
		Integer result = buyerService.SetAdminRetailerReadYN(param.getObj(), param.getReadYN());
		return new JsonResponse<String>(true, null, result, null);
	}

	/**
	 * Set tblRetailer's status, activeYn
	 *
	 * @param setAdminRetailerInfoParameter
	 */
	@RequestMapping(value = "setadminretailerinfo", method = RequestMethod.POST)
	public JsonResponse<String> setAdminRetailerInfo(@RequestBody SetAdminRetailerInfoParameter setAdminRetailerInfoParameter) {
		buyerService.setAdminRetailerInfo(setAdminRetailerInfoParameter.getRetailerList());
		return new JsonResponse<>(true, "Saved successfully!", 1, null);
	}

	/**
	 * Set tblRetailer's detail information
	 *
	 * @param setAdminRetailerDetailParameter
	 */
	@RequestMapping(value = "setadminretailerdetail", method = RequestMethod.POST)
	public Integer setAdminRetailerDetail(@RequestBody SetAdminRetailerDetailParameter setAdminRetailerDetailParameter) {
		try {
			if ("Y".equalsIgnoreCase(setAdminRetailerDetailParameter.getRetailerDetail().getActive()) &&
					setAdminRetailerDetailParameter.getRetailerDetail().getCurrentStatus() == 5) {
				return -2;
			}

			return buyerService.setAdminRetailerDetail(setAdminRetailerDetailParameter);
		} catch (RuntimeException e) {
			return -99;
		}
	}

	@RequestMapping(value = "getadminretailerdetail", method = RequestMethod.POST)
	public JsonResponse<RetailerDetailResponse> getAdminRetailerDetail(@RequestBody GetAdminRetailerDetailParameter getAdminRetailerDetailParameter) {
		JsonResponse<RetailerDetailResponse> response = new JsonResponse<RetailerDetailResponse>();
		RetailerDetailResponse adminRetailerDetail = renewalBuyerService.getAdminRetailerDetail(getAdminRetailerDetailParameter.getRetailerId());

		response.setSuccess(true);
		response.setData(adminRetailerDetail);

		return response;
	}

	/**
	 * Set tblRetailer's file attachment
	 *
	 * @param setAttachedFileParameter
	 */
	@RequestMapping(value = "setattachedfile", method = RequestMethod.POST)
	public Integer setAttachedFile(@RequestBody SetAttachedFileParameter setAttachedFileParameter) {
		try {
			if (setAttachedFileParameter.getRetailerId() == null || StringUtils.isEmpty(setAttachedFileParameter.getFileType())) {
				return -1;
			}

			return buyerService.setAttachedFile(setAttachedFileParameter);
		} catch (RuntimeException e) {
			return -99;
		}
	}

	@RequestMapping(value = "getshippingaddress",method = RequestMethod.POST)
	public JsonResponse<List<ShippingAddressResponse>> getShippingAddress(@RequestBody GetShippingAddressParameter getShippingAddressParameter) {
		JsonResponse<List<ShippingAddressResponse>> response = new JsonResponse();

		List<ShippingAddressResponse> shippingAddress = renewalBuyerService.getShippingAddress(getShippingAddressParameter);
		response.setData(shippingAddress);
		response.setSuccess(true);

		return response;
	}

	@RequestMapping(value = "getfraudnotice",method = RequestMethod.POST)
	public JsonResponse<List<FraudNoticeResponse>> getFraudNotice(@RequestBody GetFraudNoticeParameter parameter) {
		JsonResponse<List<FraudNoticeResponse>> response = new JsonResponse();

		List<FraudNoticeResponse> fraudNotice = renewalBuyerService.getFraudNotice(parameter);
		response.setData(fraudNotice);
		response.setSuccess(true);

		return response;
	}

	@RequestMapping(value = "getcommunicationreason",method = RequestMethod.POST)
	public JsonResponse<List<ListCommunicationReasonResponse>> getCommunicationreason() {
		JsonResponse<List<ListCommunicationReasonResponse>> response = new JsonResponse();

		List<ListCommunicationReasonResponse> communicationReason = renewalBuyerService.getCommunicationReason();
		response.setData(communicationReason);
		response.setSuccess(true);

		return response;
	}

	@RequestMapping(value = "getcommunicationlog",method = RequestMethod.POST)
	public JsonResponse<List<CommunicationLogResponse>> getCommunicationLog(@RequestBody GetCommunicationLogParameter parameter) {
		JsonResponse<List<CommunicationLogResponse>> response = new JsonResponse();

		List<CommunicationLogResponse> communicationLog = renewalBuyerService.getCommunicationLog(parameter);
		response.setData(communicationLog);
		response.setSuccess(true);

		return response;
	}

	@RequestMapping(value = "getadminlogemailsent", method = RequestMethod.POST)
	public JsonResponse<LogSentEmailResponse> getAdminlogEmailSent(@RequestBody GetAdminLogEmailSentParameter parameter) {
		JsonResponse<LogSentEmailResponse> response = new JsonResponse();
		LogSentEmailResponse adminLogEmailSent = renewalBuyerService.getAdminLogEmailSent(parameter);

		response.setSuccess(true);
		response.setData(adminLogEmailSent);

		return response;
	}
}
