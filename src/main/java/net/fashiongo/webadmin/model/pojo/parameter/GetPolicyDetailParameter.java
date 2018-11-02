package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author DAHYE
 *
 */
public class GetPolicyDetailParameter extends PageSizeParameter {

	@ApiModelProperty(required = false, example="CmpanyName")
	@JsonProperty("searchitem")
	private String searchItem;

	@ApiModelProperty(required = false, example="fashiongo")
	@JsonProperty("searchtxt")
	private String searchTxt;

	@ApiModelProperty(required = false, example="6")
	@JsonProperty("policyid")
	private Integer policyID;
	
	@Override
	public Integer getPageSize() {
		return pageSize == null ? 1000: pageSize;
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
