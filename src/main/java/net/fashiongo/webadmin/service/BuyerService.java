package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.fashiongo.webadmin.dao.primary.RetailerCompanyRepository;
import net.fashiongo.webadmin.model.pojo.buyer.parameter.SetAdminRetailerReadYNParameter;
import net.fashiongo.webadmin.model.primary.RetailerCompany;
import net.fashiongo.webadmin.utility.JsonResponse;
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
	 * Description Example
	 * @since 2018. 11. 22.
	 * @author Reo
	 * @param companyName
	 * @return
	 */
	public List<RetailerCompany> GetRetailerListForCompanyName(String companyName) {
		List<RetailerCompany> result = retailerCompanyRepository.findByActiveAndCompanyNameStartingWithOrderByCompanyName("Y", companyName);
		
		return result;
	}
	
	/**
	 * SetAdminRetailerReadYN
	 * 
	 * @since 2018. 11. 28.
	 * @author Dahye
	 * @param SetAdminRetailerReadYNParameter
	 * @return Integer
	 */
	public Integer SetAdminRetailerReadYN(List<Integer>retailerIDList, Boolean readYN) {
		if(retailerIDList.size() < 1) return -1;
		for(Integer id : retailerIDList) {
			RetailerCompany retailer = retailerCompanyRepository.findOneByRetailerID(id);
			if(retailer == null) {
				return -1;
			}
			retailer.setOperatorRead(readYN);
			retailerCompanyRepository.save(retailer);
		}
		return 1;
	}}

