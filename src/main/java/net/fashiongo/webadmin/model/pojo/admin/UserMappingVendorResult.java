package net.fashiongo.webadmin.model.pojo.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
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
