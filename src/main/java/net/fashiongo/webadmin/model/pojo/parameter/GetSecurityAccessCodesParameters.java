package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author JungHwan
 */
public class GetSecurityAccessCodesParameters {
	@ApiModelProperty(required = false, example="")
	private String accessCode;
	@ApiModelProperty(required = false, example="2015-01-01")
	private String sDate;
	@ApiModelProperty(required = false, example="2020-01-01")
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
