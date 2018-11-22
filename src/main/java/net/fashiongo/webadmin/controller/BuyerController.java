package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.buyer.parameter.SetModifyPasswordParameter;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.primary.RetailerCompany;
import net.fashiongo.webadmin.service.BuyerService;
import net.fashiongo.webadmin.service.UserService;
import net.fashiongo.webadmin.utility.JsonResponse;

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
	 * SetAdminRetailerReadYN
	 * 
	 * @since 2018. 11. 12.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="setadminretailerreadyn", method=RequestMethod.POST)
	public void setAdminRetailerReadYN(@RequestBody Integer parameters) {
		
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
}
