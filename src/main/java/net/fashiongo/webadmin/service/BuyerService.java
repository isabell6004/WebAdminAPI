package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.fashiongo.webadmin.dao.primary.RetailerCompanyRepository;
import net.fashiongo.webadmin.model.primary.RetailerCompany;
;

/**
 * 
 * @author DAHYE
 *
 */
@Service
public class BuyerService extends ApiService {
	
	@Autowired
	private RetailerCompanyRepository retailerCompanyRepository;
	
	/**
	 * 
	 * SetAdminRetailerReadYN
	 * 
	 * @since 2018. 11. 12.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void setAdminRetailerReadYN(Integer parameters) {
		
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 22.
	 * @author Reo
	 * @param companyName
	 * @return
	 */
	public List<RetailerCompany> GetRetailerListForCompanyName(String companyName) {
		List<RetailerCompany> result = retailerCompanyRepository.findByActiveAndCompanyNameStartingWithOrderByCompanyName("Y", companyName);
		
		return result;
	}}

