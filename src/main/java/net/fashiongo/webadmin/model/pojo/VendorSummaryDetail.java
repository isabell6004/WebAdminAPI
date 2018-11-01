package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VendorSummaryDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("WholeSalerID")
	private String wholeSalerID;
	@JsonProperty("CompanyName")
	private String companyName;
	public String getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(String wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
