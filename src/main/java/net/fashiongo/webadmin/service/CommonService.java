package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.AdPageRepository;
import net.fashiongo.webadmin.dao.primary.AdPageSpotRepository;
import net.fashiongo.webadmin.dao.primary.SecurityMenuRepository;
import net.fashiongo.webadmin.dao.primary.SecurityUserRepository;
import net.fashiongo.webadmin.dao.primary.TopCategoriesRepository;
import net.fashiongo.webadmin.model.pojo.response.GetBidAdPagesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCountryStatesResponse;
import net.fashiongo.webadmin.model.primary.AdPage;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
import net.fashiongo.webadmin.model.primary.SecurityMenu;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.model.primary.TopCategories;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

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
	
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;
	
	/**
	 * 
	 * Get MenuID
	 * @since 2018. 10. 4.
	 * @author Dahye Jeong
	 * @param pageName
	 * @return SecurityMenu
	 */
	public Integer GetMenuID(String pageName) {
		SecurityMenu result = new SecurityMenu();
		result = securityMenuRepository.findOneByRoutePath(pageName);
		return result.getMenuID();
	}
	
	/**
	 * 
	 * Get Server Heart Beat
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param q
	 * @return "Spring Boot"
	 */
	public String GetServerHeartBeat(Long q) {
		return "Spring Boot";
	}
	
	/**
	 * 
	 * Get Country States
	 * @since 2018. 10. 15.
	 * @author Dahye Jeong
	 * @param countryAbbrev
	 * @return JsonResponse<GetCountryStatesResponse>
	 */
	@SuppressWarnings("unchecked")
//	@Cacheable(value="GetCountryStates", key="#countryAbbrev")
	public JsonResponse<GetCountryStatesResponse> GetCountryStates(String countryAbbrev) {
		JsonResponse<GetCountryStatesResponse> result = httpClient.get("location/countries/".concat(countryAbbrev));
		return result;
	}
	
	/**
	 * 
	 * Get Top Categories
	 * @since 2018. 10. 11.
	 * @author Dahye Jeong
	 * @param 
	 * @return List<TopCategories>
	 */
	@Cacheable(value="GetTopCategories")
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
		LocalDateTime nowDate = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0, 0);
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
	 * @return List<SecurityUser>
	 */
	public List<SecurityUser> GetSecurityUser() {
		List<SecurityUser> result =  securityUserRepository.findAllByOrderByActiveDescUserName();
		
		return result;
	}
}
