package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;

public class VendorSummaryDetail implements Serializable{
	private static final long serialVersionUID = 1L;

	private String wholeSalerID;
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
