package net.fashiongo.webadmin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.primary.VendorAutocomplete;
import net.fashiongo.webadmin.model.primary.VendorCompany;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * @author roy
 */
@RestController
@RequestMapping(value = "/vendor", produces = "application/json")
public class VendorController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	VendorService vendorService;

	/**
	 * Get vendor list
	 * 
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	@RequestMapping(value = "getvendorlistall", method = RequestMethod.POST)
	public JsonResponse<List<VendorCompany>> getVendorListAll() {

		List<VendorCompany> vendors = vendorService.getVendorList();
		return new JsonResponse<List<VendorCompany>>(true, null, 0, vendors);
	}

	@GetMapping(value = "/autocomplete/{prefix:.+}")
	public JsonResponse<List<VendorAutocomplete>> getVendorsAutoomplete(@PathVariable("prefix") String prefix) {
		JsonResponse<List<VendorAutocomplete>> response = new JsonResponse<>(false, null, null);

		try {
			List<VendorAutocomplete> vendors = vendorService.getVendorsAutoomplete(prefix);
			response.setSuccess(true);
			response.setData(vendors);
		} catch (Exception ex) {
			logger.error("Exception Error: ", ex);
			response.setMessage(ex.getMessage());
		}

		return response;
	}

}
