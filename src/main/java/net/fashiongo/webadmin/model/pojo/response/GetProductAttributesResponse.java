package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.CodeData;
import net.fashiongo.webadmin.model.pojo.Total;

public class GetProductAttributesResponse {
	@JsonProperty("Table")
	private List<Total> recCnt;
	
	@JsonProperty("Table1")
	private List<CodeData> codeDataList;

	public List<Total> getRecCnt() {
		return recCnt;
	}

	public void setRecCnt(List<Total> recCnt) {
		this.recCnt = recCnt;
	}

	public List<CodeData> getCodeDataList() {
		return codeDataList;
	}

	public void setCodeDataList(List<CodeData> codeDataList) {
		this.codeDataList = codeDataList;
	}
	
	
}
