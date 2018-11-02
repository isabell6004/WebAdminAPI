package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.RetailerNews;
import net.fashiongo.webadmin.model.pojo.Total;

public class GetRetailerNewsResponse {
	@JsonProperty("Table")
	private List<Total> recCnt;
	
	@JsonProperty("Table1")
	private List<RetailerNews> codeDataList;
	
	@JsonProperty("success")
	private Boolean success;

	public List<Total> getRecCnt() {
		return recCnt;
	}

	public void setRecCnt(List<Total> recCnt) {
		this.recCnt = recCnt;
	}

	public List<RetailerNews> getCodeDataList() {
		return codeDataList;
	}

	public void setCodeDataList(List<RetailerNews> codeDataList) {
		this.codeDataList = codeDataList;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
}