package net.fashiongo.webadmin.model.pojo.admin.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author JungHwan
 */
public class GetSecurityAccessCodesParameters {
	@ApiModelProperty(required = false, example=" ")
	@JsonProperty("accesscode")
	private String accessCode;
	
	@ApiModelProperty(required = false, example="2015-01-01")
	@JsonProperty("sdate")
	private String sDate;
	
	@ApiModelProperty(required = false, example="2020-01-01")
	@JsonProperty("edate")
	private String eDate;
	
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
}
