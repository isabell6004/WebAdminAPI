package net.fashiongo.webadmin.model.pojo.common;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author DAHYE
 *
 */
public class Result {
	@JsonProperty("Result")
	private Integer result;

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
}
