package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author DAHYE
 *
 */
public class GetPolicyDetailParameter {
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("pagenum")
	private Integer pageNum;

	@ApiModelProperty(required = false, example="15")
	@JsonProperty("pagesize")
	private Integer pageSize;

	@ApiModelProperty(required = false, example="CompanyName")
	@JsonProperty("searchitem")
	private String searchItem;

	@ApiModelProperty(required = false, example="fashiongo")
	@JsonProperty("searchtxt")
	private String searchTxt;

	@ApiModelProperty(required = false, example="6")
	@JsonProperty("policyid")
	private Integer policyID;

	public Integer getPageNum() {
		return pageNum == null ? 1 : pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize == null ? 1000: pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	public Integer getPolicyID() {
		return policyID;
	}

	public void setPolicyID(Integer policyID) {
		this.policyID = policyID;
	}

	 
}
