package net.fashiongo.webadmin.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.parameter.SetModifyPasswordParameter;
import net.fashiongo.webadmin.service.BuyerService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author DAHYE
 *
 */
@RestController
@RequestMapping(value = "/buyer", produces = "application/json")
public class BuyerController {
	@Autowired BuyerService buyerService;
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
	public JsonResponse SetModifyPassword(@RequestParam String userid, String newpwd) {
		SetModifyPasswordParameter parameters = new SetModifyPasswordParameter();
		userid = StringUtils.isEmpty(userid) ? "-1" : userid;
		newpwd = StringUtils.isEmpty(newpwd) ? "-2" : newpwd;
		parameters.setUserName(userid);
		parameters.setNewPassword(newpwd);
		JsonResponse result = buyerService.SetModifyPassword(parameters);
		return result;
	}
}
