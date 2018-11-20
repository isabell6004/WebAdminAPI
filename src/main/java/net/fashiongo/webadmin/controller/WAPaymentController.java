package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.service.WAPaymentService;

/**
 * 
 * @author DAHYE
 *
 */
@RestController
@RequestMapping(value = "/payment", produces = "application/json")
public class WAPaymentController {
	@Autowired
	private WAPaymentService waPaymentService;
	/**
	 * getPaymentStatusSearchOption
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return GetPaymentStatusSearchOptionResponse
	 */
	@RequestMapping(value = "getPaymentStatusSearchOption", method = RequestMethod.POST)
	public net.fashiongo.webadmin.utility.JsonResponse<GetPaymentStatusSearchOptionResponse> getPaymentStatusSearchOption() {
		GetPaymentStatusSearchOptionResponse result = waPaymentService.getPaymentStatusSearchOption();
		
		return new net.fashiongo.webadmin.utility.JsonResponse<GetPaymentStatusSearchOptionResponse>(true, null, 0, result);
	}
}
