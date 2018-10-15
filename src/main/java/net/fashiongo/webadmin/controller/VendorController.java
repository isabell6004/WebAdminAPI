package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.fashiongo.webadmin.model.primary.VendorCompany;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * @author roy
 */
@RestController
@RequestMapping(value="/vendor", produces = "application/json")
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	/**
	 * Get vendor list
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	@RequestMapping(value="getvendorlistall", method=RequestMethod.POST)
	public JsonResponse<List<VendorCompany>> getVendorListAll() {
		
		List<VendorCompany> vendors = vendorService.getVendors();	
		return new JsonResponse<List<VendorCompany>>(true, null, 0, vendors);
	}
}
