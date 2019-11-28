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
import net.fashiongo.webadmin.utility.Utility;
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

	@RequestMapping(value = "getorderhistorystatistics", method = RequestMethod.POST)
	public JsonResponse<GetOrderHistoryStatisticsResponse> getOrderHistoryStatistics(@RequestBody GetOrderHistoryStatisticsParameter parameter) {
		JsonResponse<GetOrderHistoryStatisticsResponse> response = new JsonResponse();

		GetOrderHistoryStatisticsResponse orderHistoryStatistics = renewalBuyerService.getOrderHistoryStatistics(parameter);

		response.setSuccess(true);
		response.setData(orderHistoryStatistics);

		return response;
	}

	@RequestMapping(value = "getinaccessiblevendors", method = RequestMethod.POST)
	public JsonResponse<GetInaccessibleVendorsResponse> getInaccessibleVendors(@RequestBody GetInaccessibleVendorsParameter parameter) {
		JsonResponse<GetInaccessibleVendorsResponse> response = new JsonResponse();

		GetInaccessibleVendorsResponse inaccessibleVendors = renewalBuyerService.getInaccessibleVendors(parameter);

		response.setSuccess(true);
		response.setData(inaccessibleVendors);

		return response;
	}

	@RequestMapping(value = "getshoppingbag", method = RequestMethod.POST)
	public JsonResponse<GetShoppingBagResponse> getShoppingBag(@RequestBody GetShoppingBagParameter parameter) {
		JsonResponse<GetShoppingBagResponse> response = new JsonResponse();

		GetShoppingBagResponse shoppingBag = renewalBuyerService.getShoppingBag(parameter);

		response.setSuccess(true);
		response.setData(shoppingBag);

		return response;
	}

	@RequestMapping(value = "getcreditcard", method = RequestMethod.POST)
	public JsonResponse<List<CreditCard>> getCreditcard(@RequestBody GetCreditCardParameter parameter) {
		JsonResponse<List<CreditCard>> response = new JsonResponse();

		List<CreditCard> creditCardList = renewalBuyerService.getCreditCard(parameter);

		response.setSuccess(true);
		response.setData(creditCardList);

		return response;
	}

	@RequestMapping(value = "getstorecredit", method = RequestMethod.POST)
	public JsonResponse<GetStoreCreditResponse> getStoreCredit(@RequestBody GetStoreCreditParameter parameter) {
		JsonResponse<GetStoreCreditResponse> response = new JsonResponse();

		GetStoreCreditResponse storeCreditResponse = renewalBuyerService.getStoreCredit(parameter);

		response.setSuccess(true);
		response.setData(storeCreditResponse);

		return response;
	}

	@RequestMapping(value = "getorderhistory", method = RequestMethod.POST)
	public JsonResponse<OrderHistoryResponse> getOrderHistory(@RequestBody GetOrderHistoryParameter parameter) {
		JsonResponse<OrderHistoryResponse> response = new JsonResponse();

		OrderHistoryResponse orderHistory = renewalBuyerService.getOrderHistory(parameter);

		response.setSuccess(true);
		response.setData(orderHistory);

		return response;
	}

	@RequestMapping(value = "delinaccessiblevendors", method = RequestMethod.POST)
	public JsonResponse<Integer> delInaccessibleVendors(@RequestBody DelInaccessibleVendorsParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();

		Integer result = renewalBuyerService.delInaccessibleVendors(parameter);

		response.setSuccess(true);
		response.setData(result);

		return response;
	}

	@RequestMapping(value = "setinaccessiblevendors", method = RequestMethod.POST)
	public JsonResponse<Integer> setInaccessibleVendors(@RequestBody SetInaccessibleVendorsParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		String username = Utility.getUsername();
		Integer result = renewalBuyerService.setInaccessibleVendors(parameter,username);

		response.setSuccess(true);
		response.setData(result);

		return response;
	}

	@RequestMapping(value = "delstorecredit", method = RequestMethod.POST)
	public JsonResponse<Integer> delStoreCredit(@RequestBody DelStoreCreditParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		String username = Utility.getUsername();
		Integer result = renewalBuyerService.delStoreCredit(parameter,username);

		response.setSuccess(true);
		response.setData(result);

		return response;
	}

	@RequestMapping(value = "setcartitem", method = RequestMethod.POST)
	public JsonResponse<Integer> setCartItem(@RequestBody SetCartItemParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		Integer count = parameter.getCartItems().size();

		for (SetCartItem cartItem : parameter.getCartItems()) {
			Integer retValue = renewalBuyerService.setCartItem(cartItem);
			if(retValue == 1) {
				count--;
			}
		}

		response.setSuccess(true);
		response.setData(count);

		return response;
	}

	@RequestMapping(value = "delcartitem", method = RequestMethod.POST)
	public JsonResponse<Integer> delCartItem(@RequestBody DelCartItemParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		Integer count = parameter.getCartIds().size();

		for (Integer cartId : parameter.getCartIds()) {
			Integer retValue = renewalBuyerService.delCartItem(cartId);
			if(retValue == 1) {
				count--;
			}
		}

		response.setSuccess(true);
		response.setData(count);

		return response;
	}

	@RequestMapping(value = "setbillinginfo", method = RequestMethod.POST)
	public JsonResponse<Integer> setBillingInfo(@RequestBody SetBillingInfoParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		BillingInfo billingInfo = parameter.getBillingInfo();
		String username = Utility.getUsername();
		Integer retValue = renewalBuyerService.setBillingInfo(billingInfo,username);

		response.setSuccess(true);
		response.setData(retValue);

		return response;
	}

	@RequestMapping(value = "setshippinginfo", method = RequestMethod.POST)
	public JsonResponse<Integer> setShippingInfo(@RequestBody SetShippingInfoParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		ShippingInfo shippingInfo = parameter.getShippingInfo();
		String username = Utility.getUsername();
		Integer retValue = renewalBuyerService.setShippingInfo(shippingInfo,username);

		response.setSuccess(true);
		response.setData(retValue);

		return response;
	}

	@RequestMapping(value = "setshippingstatus", method = RequestMethod.POST)
	public JsonResponse<Integer> setShippingStatus(@RequestBody SetShippingStatusParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		String username = Utility.getUsername();
		Integer retValue = renewalBuyerService.setShippingStatus(parameter,username);

		response.setSuccess(true);
		response.setData(retValue);

		return response;
	}

	@RequestMapping(value = "setcommunicationlog", method = RequestMethod.POST)
	public JsonResponse<Integer> setCommunicationLog(@RequestBody SetCommunicationLogParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		Integer retValue = renewalBuyerService.setCommunicationLog(parameter);

		response.setSuccess(true);
		response.setData(retValue);

		return response;
	}

	@RequestMapping(value = "setaccountlockout", method = RequestMethod.POST)
	public JsonResponse<Integer> setAccountLockOut(@RequestBody SetAccountLockOutParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		Integer retValue = renewalBuyerService.setAccountLockOut(parameter);

		response.setSuccess(true);
		response.setData(retValue);

		return response;
	}

	@RequestMapping(value = "setlogemailsent", method = RequestMethod.POST)
	public JsonResponse<Integer> setLogEmailSent(@RequestBody SetLogEmailSentParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse();
		String username = Utility.getUsername();
		Integer retValue = renewalBuyerService.setLogEmailSent(parameter,username);

		response.setSuccess(true);
		response.setData(retValue);

		return response;
	}

	@RequestMapping(value = "sendbuyeremail", method = RequestMethod.POST)
	public JsonResponse sendBuyerEmail(@RequestBody SendBuyerEmailParameter parameter) {

		JsonResponse response = renewalBuyerService.sendBuyerEmail(parameter);

		return response;
	}

	@RequestMapping(value = "setsavedlist", method = RequestMethod.POST)
	public JsonResponse<Integer> setSavedList(@RequestBody SetSavedListParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse<>();
		String username = Utility.getUsername();

		Integer result = renewalBuyerService.setSavedList(parameter,username);
		response.setSuccess(true);
		response.setData(result);

		return response;
	}

	@RequestMapping(value = "getsavedlist", method = RequestMethod.POST)
	public JsonResponse<SavedListResponse> getSavedList(@RequestBody GetSavedListParameter parameter) {
		JsonResponse<SavedListResponse> response = new JsonResponse<>();

		SavedListResponse result = renewalBuyerService.getSavedList(parameter);
		response.setSuccess(true);
		response.setData(result);

		return response;
	}

	@RequestMapping(value = "delsavedlist", method = RequestMethod.POST)
	public JsonResponse<Integer> delSavedList(@RequestBody DelSavedListParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse<>();
		String username = Utility.getUsername();

		Integer result = renewalBuyerService.delSavedList(parameter,username);
		response.setSuccess(true);
		response.setData(result);

		return response;
	}

	@RequestMapping(value = "setfilterlist", method = RequestMethod.POST)
	public JsonResponse<Integer> setFilterList(@RequestBody SetFilterListParameter parameter) {
		JsonResponse<Integer> response = new JsonResponse<>();
		String username = Utility.getUsername();

		Integer result = renewalBuyerService.setFilterList(parameter,username);
		response.setSuccess(true);
		response.setData(result);

		return response;
	}

	@RequestMapping(value = "getadminretailer", method = RequestMethod.POST)
	public JsonResponse getAdminretailer(@RequestBody GetAdminRetailerParameter parameter) {
		JsonResponse response = renewalBuyerService.getAdminretailer(parameter);

		return response;
	}

	@RequestMapping(value = "getmodifiedbybuyerread", method = RequestMethod.POST)
	public JsonResponse getModifiedByBuyerRead(@RequestBody GetModifiedByBuyerReadParameter parameter) {
		String username = Utility.getUsername();
		JsonResponse response = renewalBuyerService.getModifiedByBuyerRead(parameter,username);

		return response;
	}
}
