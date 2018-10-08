package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.service.CommonService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author JungHwan
 */
@RestController
@RequestMapping(value = "/common", produces = "application/json")
public class CommonController {
	@Autowired
	CommonService commonService;

	@RequestMapping(value = "getbidadpages", method = RequestMethod.POST)
	public void GetBidAdPages() {
		
	}
	
	@RequestMapping(value = "getbidadpagespots", method = RequestMethod.POST)
	public void GetBidAdPageSpots() {
		
	}
	
	@RequestMapping(value = "getbidadpagespotscombined", method = RequestMethod.POST)
	public void GetBidAdPageSpotsCombined() {
		
	}
	
	/**
	 * Get Menu ID
	 * 
	 * @since 2018. 10. 05.
	 * @author DAHYE
	 * @param Page Name
	 * @return Page id
	 * @throws Exception
	 */
	@RequestMapping(value = "getmenuid", method = RequestMethod.POST)
	public JsonResponse<Integer> GetMenuID(@RequestParam("PageName") String pageName) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, null, null);
		Integer result = commonService.GetMenuID(pageName);
		results.setData(result);
		return results;
	}
	
	@RequestMapping(value = "getserverheartbeat", method = RequestMethod.POST)
	public void GetServerHeartBeat() {
		
	}
	
	@RequestMapping(value = "getcountrystates", method = RequestMethod.POST)
	public void GetCountryStates() {
		
	}
	
	@RequestMapping(value = "gettopcategories", method = RequestMethod.POST)
	public void GetTopCategories() {
		
	}
	
}
