package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class GetVendorBlockListParameter {
	@JsonProperty("SearchType")
	private String searchType;
	
	@JsonProperty("SearchKeyword")
	private String searchKeyword;

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
	
	
}
