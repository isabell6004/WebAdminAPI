package net.fashiongo.webadmin.model.pojo.parameter;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.CodeData;

/**
 * 
 * @author Reo
 *
 */
public class SetProductAttributesParameter {
	@JsonProperty("tabno")
	private Integer tabNo;
	
	@JsonProperty("btype")
	private String bType;
	
	@JsonProperty("codeid")
	private Integer codeID;
	
	@JsonProperty("attrname")
	private String attrName;
	
	@JsonProperty("active")
	private Boolean active;
	
	@JsonProperty("obj")
	private List<CodeData> codeDataList;

	public Integer getTabNo() {
		return tabNo;
	}

	public void setTabNo(Integer tabNo) {
		this.tabNo = tabNo;
	}

	public String getbType() {
		return bType;
	}

	public void setbType(String bType) {
		this.bType = bType;
	}

	public Integer getCodeID() {
		return codeID;
	}

	public void setCodeID(Integer codeID) {
		this.codeID = codeID;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Boolean getActive() {
		return Boolean.TRUE.equals(active) ? true : false;
	}

	public void setActive(Boolean active) {
		this.active = Boolean.TRUE.equals(active) ? true : false;
	}

	public List<CodeData> getCodeDataList() {
		return codeDataList;
	}

	public void setCodeDataList(List<CodeData> codeDataList) {
		this.codeDataList = codeDataList;
	}
}
