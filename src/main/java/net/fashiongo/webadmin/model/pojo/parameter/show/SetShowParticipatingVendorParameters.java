/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter.show;

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

}
