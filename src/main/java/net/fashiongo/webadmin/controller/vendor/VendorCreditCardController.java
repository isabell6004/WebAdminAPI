package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.DelVendorCreditcardParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetVendorCreditCardListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorCreditCardListResponse;
import net.fashiongo.webadmin.model.primary.CreditCardType;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.UserService;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import net.fashiongo.webadmin.service.vendor.VendorInfoService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorCreditCardController {

	private final VendorService vendorService;

    public VendorCreditCardController(VendorService vendorService) {
    	this.vendorService = vendorService;
	}

	/**
	 * getVendorCreditCardList
	 *
	 * @since 2018. 11. 12.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@PostMapping("vendor/getvendorcreditcardList")
	public JsonResponse<GetVendorCreditCardListResponse> getVendorCreditCardList(
			@Valid @RequestBody GetVendorCreditCardListParameter parameters) {
		GetVendorCreditCardListResponse result = vendorService.getVendorCreditCardList(parameters.getOrderBy());
		return new JsonResponse<GetVendorCreditCardListResponse>(true, null, 0, result);
	}

	/**
	 * getCreditCardType
	 *
	 * @since 2018. 11. 12.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@RequestMapping(value="vendor/getcreditcardtype", method=RequestMethod.POST, produces = "application/json")
	public JsonResponse<List<CreditCardType>> getCreditCardType() {
		List<CreditCardType> result = vendorService.getCreditCardType();
		return new JsonResponse<List<CreditCardType>>(true, null, 0, result);
	}

	/**
	 * DelVendorCreditCard
	 *
	 * @since 2018. 11. 19.
	 * @author Dahye
	 * @param DelVendorCreditcardParameter
	 * @return
	 */
	@RequestMapping(value="vendor/delvendorcreditcard", method=RequestMethod.POST, produces = "application/json")
	public JsonResponse<String> delVendorCreditCard(@RequestBody DelVendorCreditcardParameter parameters) {
		vendorService.delVendorCreditCard(parameters);
		return new JsonResponse<String>();
	}

}
	
