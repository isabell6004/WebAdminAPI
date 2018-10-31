package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.Result;
/**
 * 
 * @author DAHYE
 *
 */
public class DeleteCommunicationReasonResponse {
	@JsonProperty("Table")
	private List<Result> result;

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}
	
	
}
