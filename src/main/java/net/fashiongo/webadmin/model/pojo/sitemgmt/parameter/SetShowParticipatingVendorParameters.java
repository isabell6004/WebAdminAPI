/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Set ShowSchedule Parameters
 * 
 * @author Sanghyup Kim
 */
public class SetShowParticipatingVendorParameters {
	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("mapId")
	private Integer mapId;

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("planId")
	private Integer planId;

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("wholesalerId")
	private Integer wholesalerId;

	@ApiModelProperty(required = true, example = "1.0")
	@JsonProperty("commissionRate")
	private double commissionRate;

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("rackCount")
	private Integer rackCount;

	@ApiModelProperty(required = true, example = "1.0")
	@JsonProperty("fee")
	private BigDecimal fee;

	@ApiModelProperty(required = true, example = "1.0")
	@JsonProperty("discountAmount")
	private BigDecimal discountAmount;

	@ApiModelProperty(required = true, example = "1.0")
	@JsonProperty("isRegularCommission")
	private Boolean isRegularCommission;

	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getWholesalerId() {
		return wholesalerId;
	}

	public void setWholesalerId(Integer wholesalerId) {
		this.wholesalerId = wholesalerId;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Integer getRackCount() {
		return rackCount;
	}

	public void setRackCount(Integer rackCount) {
		this.rackCount = rackCount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Boolean getIsRegularCommission() {
		return isRegularCommission;
	}

	public void setIsRegularCommission(Boolean isRegularCommission) {
		this.isRegularCommission = isRegularCommission;
	}

}
