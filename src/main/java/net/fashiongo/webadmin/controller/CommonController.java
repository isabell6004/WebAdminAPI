package net.fashiongo.webadmin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author JungHwan
 */
@RestController
@RequestMapping(value = "/common", produces = "application/json")
public class CommonController {
	@RequestMapping(value = "getbidadpages", method = RequestMethod.POST)
	public void GetBidAdPages() {
		
	}
	
	@RequestMapping(value = "getbidadpagespots", method = RequestMethod.POST)
	public void GetBidAdPageSpots() {
		
	}
	
	@RequestMapping(value = "getbidadpagespotscombined", method = RequestMethod.POST)
	public void GetBidAdPageSpotsCombined() {
		
	}
}
