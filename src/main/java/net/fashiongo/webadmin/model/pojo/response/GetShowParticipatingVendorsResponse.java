/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.common.SingleValueResult;
import net.fashiongo.webadmin.model.primary.MapShowSchedulePromotionPlanVendor;

/**
 * @author Sanghyup Kim
 */
@JsonSerialize
public class GetShowParticipatingVendorsResponse {
	@JsonProperty("Table")
	private List<SingleValueResult> singleValueResultList;

	@JsonProperty("Table1")
	private List<MapShowSchedulePromotionPlanVendor> showSchedulePromotionPlanVendorList;

	public List<MapShowSchedulePromotionPlanVendor> getShowSchedulePromotionPlanVendorList() {
		return showSchedulePromotionPlanVendorList;
	}

	public void setShowSchedulePromotionPlanVendorList(
			List<MapShowSchedulePromotionPlanVendor> showSchedulePromotionPlanVendorList) {
		this.showSchedulePromotionPlanVendorList = showSchedulePromotionPlanVendorList;
	}

	public List<SingleValueResult> getSingleValueResultList() {
		return singleValueResultList;
	}

	public void setSingleValueResultList(List<SingleValueResult> singleValueResultList) {
		this.singleValueResultList = singleValueResultList;
	}

}
