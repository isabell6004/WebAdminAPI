package net.fashiongo.webadmin.model.pojo.admin.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author JungHwan
 */
public class SetSecurityAccessCodeParameters {
	@ApiModelProperty(required = false, example="")
	@JsonProperty("codeid")
	private Integer codeID;
	
	@ApiModelProperty(required = false, example="hgfhgf")
	@JsonProperty("accesscode")
	private String accessCode;
	
	@ApiModelProperty(required = false, example="09/23/2018")
	@JsonProperty("expiredon")
	private String expiredOn;

	public Integer getCodeID() {
		return codeID;
	}

	public void setCodeID(Integer codeID) {
		this.codeID = codeID;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(String expiredOn) {
		this.expiredOn = expiredOn;
	}
}
