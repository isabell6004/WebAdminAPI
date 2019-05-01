package net.fashiongo.webadmin.model.pojo.bid.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SetBidCancelParameter {

	@JsonProperty("bidid")
	@ApiModelProperty(required = true, example="1")
	private String bidid;
	
	@JsonProperty("adminid")
	@ApiModelProperty(required = true, example="admin")
	private String adminid;
	
	public Integer getBidid() {
		return StringUtils.isEmpty(bidid) ? null : Integer.parseInt(bidid);
	}
	
	public String getAdminid() {
		return StringUtils.isEmpty(adminid) ? "" : adminid;
	}
	
	public void setBidid(String bidid) {
		this.bidid = bidid;
	}
	
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
}
