package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.AdSettingList;
import net.fashiongo.webadmin.model.pojo.AdSettingSubList;

/**
 * @author Nayeon Kim
 */
public class GetADSettingResponse {
	@JsonProperty("Table")
	private List<AdSettingSubList> adsettingsublist;

	@JsonProperty("Table1")
	private List<AdSettingList> adsettinglist;

	public List<AdSettingSubList> getAdsettingsublist() {
		return adsettingsublist;
	}

	public void setAdsettingsublist(List<AdSettingSubList> adsettingsublist) {
		this.adsettingsublist = adsettingsublist;
	}

	public List<AdSettingList> getAdsettinglist() {
		return adsettinglist;
	}

	public void setAdsettinglist(List<AdSettingList> adsettinglist) {
		this.adsettinglist = adsettinglist;
	}
}
