package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */

public class GetVendorBlockListParameter {

	@JsonProperty("PageNum")
	private Integer pageNum;

	@JsonProperty("PageSize")
	private Integer pageSize;

	@JsonProperty("SearchType")
	private String searchType;
	
	@JsonProperty("SearchKeyword")
	private String searchKeyword;

	@JsonProperty("SearchTypeCode")
	private Integer searchTypeCode;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public Integer getSearchTypeCode() {
		return searchTypeCode;
	}

	public void setSearchTypeCode(Integer searchTypeCode) {
		this.searchTypeCode = searchTypeCode;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
