package net.fashiongo.webadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.AdPageRepository;
import net.fashiongo.webadmin.dao.primary.VendorListRepository;
import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * @author roy
 */
@Service
public class VendorService extends ApiService {
	@Autowired
	private VendorListRepository vendorListRepository;
	
	/**
	 * Get vendor list
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	public List<VendorCompany> getVendors() {
		return vendorListRepository.findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
	}
}
