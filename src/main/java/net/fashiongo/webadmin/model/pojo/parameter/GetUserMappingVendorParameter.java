package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class GetUserMappingVendorParameter {
	@ApiModelProperty(required = true, example="1")
	@JsonProperty("userid")
	private Integer userID;
	
	@ApiModelProperty(required = true, example="abc")
	@JsonProperty("alphabet")
	private String alphabet;
	
	@ApiModelProperty(required = true, example="FirstName")
	@JsonProperty("usercompanytypeid")
	private String companyType;
	
	@ApiModelProperty(required = true, example="1,3")
	@JsonProperty("categorys")
	private String categorys;
	
	@ApiModelProperty(required = true, example="1")
	@JsonProperty("vendortype")
	private String vendorType;
	
	@ApiModelProperty(required = true, example="test")
	@JsonProperty("vendorKeyword")
	private String vendorKeyword;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getVendorKeyword() {
		return vendorKeyword;
	}

	public void setVendorKeyword(String vendorKeyword) {
		this.vendorKeyword = vendorKeyword;
	}
	
}
