package net.fashiongo.webadmin.model.pojo.ad.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.ad.AdSettingList;
import net.fashiongo.webadmin.model.pojo.ad.AdSettingSubList;

/**
 * @author Nayeon Kim
 */
public class GetADSettingResponse {
	@JsonProperty("Table")
	private List<AdSettingSubList> adSettingSubList;

	@JsonProperty("Table1")
	private List<AdSettingList> adSettingList;

	public List<AdSettingSubList> getAdSettingSubList() {
		return adSettingSubList;
	}

	public void setAdSettingSubList(List<AdSettingSubList> adSettingSubList) {
		this.adSettingSubList = adSettingSubList;
	}

	public List<AdSettingList> getAdSettingList() {
		return adSettingList;
	}

	public void setAdSettingList(List<AdSettingList> adSettingList) {
		this.adSettingList = adSettingList;
	}
}
