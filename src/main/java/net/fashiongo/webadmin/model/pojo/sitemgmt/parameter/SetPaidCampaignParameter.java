package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.fgem.EmConfiguration;

/**
 * @author Nayeon Kim
 */
@JsonSerialize
public class SetPaidCampaignParameter {
	@JsonProperty("obj")
	List<EmConfiguration> objList;

	public List<EmConfiguration> getObjList() {
		return objList;
	}

	public void setObjList(List<EmConfiguration> objList) {
		this.objList = objList;
	}
}
