package net.fashiongo.webadmin.model.pojo.parameter;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author JungHwan
 */
public class SetSecurityAccessCodeParameters {
	@ApiModelProperty(required = false, example="")
	private Integer codeID;
	
	@ApiModelProperty(required = false, example="hgfhgf")
	private String accessCode;
	
	@ApiModelProperty(required = false, example="09/23/2018")
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
