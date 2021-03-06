/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.primary.show.ShowSchedulePromotionPlan;

/**
 * @author Sanghyup Kim
 */
@JsonSerialize
public class GetShowPromotionPlanResponse {
	@JsonProperty("promotionPlan")
	private ShowSchedulePromotionPlan showSchedulePromotionPlan;

	public ShowSchedulePromotionPlan getShowSchedulePromotionPlan() {
		return showSchedulePromotionPlan;
	}

	public void setShowSchedulePromotionPlan(ShowSchedulePromotionPlan showSchedulePromotionPlan) {
		this.showSchedulePromotionPlan = showSchedulePromotionPlan;
	}

	
}
