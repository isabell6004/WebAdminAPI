package net.fashiongo.webadmin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.AdPageRepository;
import net.fashiongo.webadmin.dao.primary.AdPageSpotRepository;
import net.fashiongo.webadmin.dao.primary.SecurityMenuRepository;
import net.fashiongo.webadmin.dao.primary.TopCategoriesRepository;
import net.fashiongo.webadmin.model.primary.AdPage;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
import net.fashiongo.webadmin.model.primary.SecurityMenu;
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
	 * @return List<AdPage>
	 */
	public List<AdPage> GetBidAdPages() {
		//List<AdPage> result=adPageRepository.findAll();
		List<AdPage> result=(List<AdPage>) adPageRepository.findAll();
		
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
		LocalDateTime localDate = LocalDateTime.now();
		List<AdPageSpot> result = adPageSpotRepository.findByActiveTrueAndBidEffectiveOnLessThanEqualAndPageIDNotAndPageIDOrderBySpotName(localDate, 0, pageId);
		
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

}
