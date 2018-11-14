package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VendorSummaryDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CompanyName")
	private String companyName;
	
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
