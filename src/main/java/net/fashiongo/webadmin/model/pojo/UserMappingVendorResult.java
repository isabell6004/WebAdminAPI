package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMappingVendorResult {
	@JsonProperty("Result")
	private Integer Result;

	public Integer getResult() {
		return Result;
	}

	public void setResult(Integer result) {
		Result = result;
	}
	
}
