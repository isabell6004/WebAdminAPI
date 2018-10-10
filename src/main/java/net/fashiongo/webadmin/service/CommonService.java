package net.fashiongo.webadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.SecurityMenuRepository;
import net.fashiongo.webadmin.dao.primary.SecurityUserRepository;
import net.fashiongo.webadmin.dao.primary.TopCategoriesRepository;
import net.fashiongo.webadmin.model.primary.SecurityMenu;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.model.primary.TopCategories;

/**
 * 
 * @author DAHYE
 *
 */
@Service
public class CommonService extends ApiService {
	@Autowired
	SecurityMenuRepository securityMenuRepository;
	
	@Autowired
	TopCategoriesRepository topCategoriesRepository;
	
	@Autowired
	SecurityUserRepository securityUserRepository;
	
	public Integer GetMenuID(String pageName) {
		SecurityMenu result = new SecurityMenu();
		result = securityMenuRepository.findOneByRoutePath(pageName);
		return result.getMenuID();
	}
	
	public void GetServerHeartBeat() {
		
	}
	
	public void GetCountryStates() {
		
	}
	
	public List<TopCategories> GetTopCategories() {
		List<TopCategories> result = (List<TopCategories>) topCategoriesRepository.findByActiveAndLvlOrderByListOrder(true, 1);
		
		return result;
	}

	/**
	 * 
	 * Get Security Users
	 * 
	 * @since 2018. 10. 10.
	 * @author Nayeon Kim
	 * @param GetSecurityUserParameter
	 * @return GetSecurityUserResponse
	 */
	public List<SecurityUser> getSecurityUser() {
		List<SecurityUser> securityUser =  securityUserRepository.findAllByOrderByActiveDesc();
		
		return securityUser;
	}
}
