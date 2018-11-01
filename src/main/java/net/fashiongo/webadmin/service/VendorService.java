package net.fashiongo.webadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.AdPageRepository;
import net.fashiongo.webadmin.dao.primary.VendorAutocompleteRepository;
import net.fashiongo.webadmin.dao.primary.VendorListRepository;
import net.fashiongo.webadmin.model.primary.VendorAutocomplete;
import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * @author roy
 */
@Service
public class VendorService extends ApiService {
	@Autowired
	private VendorListRepository vendorListRepository;
	@Autowired
	private VendorAutocompleteRepository vendorAutocompleteRepository;
	
	/**
	 * Get vendor list
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	public List<VendorCompany> getVendorList() {
		return vendorListRepository.findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
	}
	
	/**
	 * Get autocomplete search results in company name prefix
	 * created by Andy Min on 11/01/2018
	 * @param prefix
	 * @return
	 */
	public List<VendorAutocomplete> getVendorsAutoomplete(String prefix) {
		return vendorAutocompleteRepository.findByCompanyNameStartingWithOrEmailStartingWithAllIgnoreCase(prefix, prefix);
	}

}

