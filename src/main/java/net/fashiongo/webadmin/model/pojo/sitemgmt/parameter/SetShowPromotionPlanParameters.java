/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Set ShowSchedule Parameters
 * 
 * @author Sanghyup Kim
 */
public class SetShowPromotionPlanParameters {

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("planId")
	private Integer planId;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("planName")
	private String planName;

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("showScheduleId")
	private Integer showScheduleId;

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("isOnline")
	private Boolean isOnline;

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("isOffline")
	private Boolean isOffline;

	@ApiModelProperty(required = true, example = "10/20/2018")
	@JsonProperty("commissionEffectiveFrom")
	private String commissionEffectiveFrom;

	@ApiModelProperty(required = true, example = "10/31/2018")
	@JsonProperty("commissionEffectiveTo")
	private String commissionEffectiveTo;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("modifiedBy")
	private String modifiedBy;

	public Integer getPlanId() {
//		return planId;
		return planId == null ? 0 : planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getShowScheduleId() {
		return showScheduleId;
	}

	public void setShowScheduleId(Integer showScheduleId) {
		this.showScheduleId = showScheduleId;
	}

	public Boolean getIsOnline() {
//		return isOnline;
		return isOnline == null ? false : isOnline;
	}

	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Boolean getIsOffline() {
//		return isOffline;
		return isOffline == null ? false : isOffline;
	}

	public void setIsOffline(Boolean isOffline) {
		this.isOffline = isOffline;
	}

	public LocalDateTime getCommissionEffectiveFrom() {
//		return commissionEffectiveFrom;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
		return StringUtils.isEmpty(commissionEffectiveFrom) ? null : LocalDateTime.parse(commissionEffectiveFrom.concat(" 00:00:00"), formatter);
	}

	public void setCommissionEffectiveFrom(String commissionEffectiveFrom) {
		this.commissionEffectiveFrom = commissionEffectiveFrom;
	}

	public LocalDateTime getCommissionEffectiveTo() {
//		return commissionEffectiveTo;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
		return StringUtils.isEmpty(commissionEffectiveTo) ? null : LocalDateTime.parse(commissionEffectiveTo.concat(" 00:00:00"), formatter);
	}

	public void setCommissionEffectiveTo(String commissionEffectiveTo) {
		this.commissionEffectiveTo = commissionEffectiveTo;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
