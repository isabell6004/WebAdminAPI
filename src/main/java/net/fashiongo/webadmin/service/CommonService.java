package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.AdPageRepository;
import net.fashiongo.webadmin.dao.primary.AdPageSpotRepository;
import net.fashiongo.webadmin.dao.primary.SecurityMenuRepository;
import net.fashiongo.webadmin.dao.primary.SecurityUserRepository;
import net.fashiongo.webadmin.dao.primary.TopCategoriesRepository;
import net.fashiongo.webadmin.model.pojo.response.GetBidAdPagesResponse;
import net.fashiongo.webadmin.model.primary.AdPage;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
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
	AdPageRepository adPageRepository;
	
	@Autowired
	AdPageSpotRepository adPageSpotRepository;
	
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
	 * Get Bid AdPage
	 * 
	 * @since 2018. 10. 11.
	 * @author Junghwan Lee
	 * @return GetBidAdPagesResponse
	 */
	@SuppressWarnings("unchecked")
	public GetBidAdPagesResponse GetBidAdPages() {
		GetBidAdPagesResponse result = new GetBidAdPagesResponse();
		String spName = "up_wa_GetBidAdPages";
		List<Object> params = new ArrayList<Object>();

		List<Object> _result = jdbcHelper.executeSP(spName, params, AdPage.class);
		result.setAdPage(((List<AdPage>) _result.get(0)));
		
		return result;
	}
	
	/**
	 * Get Bid AdPage Spots
	 * 
	 * @since 2018. 10. 11.
	 * @author Junghwan Lee
	 * @param pageId
	 * @return List<AdPageSpot>
	 */
	public List<AdPageSpot> GetBidAdPageSpots(Integer pageId) {
		Date nowDate = new Date();
		List<AdPageSpot> result = adPageSpotRepository.findByActiveTrueAndBidEffectiveOnLessThanEqualAndPageIDNotAndPageIDOrderBySpotName(nowDate, 0, pageId);
		
		return result;
	}
	
	/**
	 * Get Bid AdPage Spots Combined 
	 * 
	 * @since 2018. 10. 11.
	 * @author Junghwan Lee
	 * @return void
	 */
	public void GetAdPageSpotsCombined() {
		
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
