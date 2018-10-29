package net.fashiongo.webadmin.model.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.ResultCode;

public class SetCreateSecurityUserResponse {
	@JsonProperty("result")
	private ResultCode resultCode;
	
	@JsonProperty("result2")
	private ResultCode resultCode2;

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public ResultCode getResultCode2() {
		return resultCode2;
	}

	public void setResultCode2(ResultCode resultCode2) {
		this.resultCode2 = resultCode2;
	}
	
		
}
