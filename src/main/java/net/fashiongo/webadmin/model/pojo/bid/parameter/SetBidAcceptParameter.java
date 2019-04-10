package net.fashiongo.webadmin.model.pojo.bid.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SetBidAcceptParameter {

	@ApiModelProperty(required = true, example="10")
	@JsonProperty("spotId")
	private String spotId;
	
	@ApiModelProperty(required = false, example="20190101")
	@JsonProperty("addate")
	private String addate;
	
	@ApiModelProperty(required = false, example="1000,1001")
	@JsonProperty("bidids")
	private String bidids;
	
	@ApiModelProperty(required = false, example="admin")
	@JsonProperty("adminid")
	private String adminid;	
	
}
