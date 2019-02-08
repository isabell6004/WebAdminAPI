package net.fashiongo.webadmin.model.pojo.vendor.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.vendor.Country;
import net.fashiongo.webadmin.model.pojo.vendor.SecurityUserName;
import net.fashiongo.webadmin.model.primary.VendorCompanyType;
import net.fashiongo.webadmin.model.primary.VendorDetailDate;

/**
 * 
 * @author Reo
 *
 */
public class GetVendorDetailInfoDataResponse {
	@JsonProperty("SessionUsrID")
	private String SessionUsrID;
	
	@JsonProperty("Table")
	private List<VendorDetailDate> vendorDetailDateList;
	
	@JsonProperty("UserName")
	private List<SecurityUserName> userName;
	
	@JsonProperty("Table2")
	private List<VendorCompanyType> vendorCompanyTypeList;
	
	@JsonProperty("Table3")
	private List<Country> countryList;

	public String getSessionUsrID() {
		return SessionUsrID;
	}

	public void setSessionUsrID(String sessionUsrID) {
		SessionUsrID = sessionUsrID;
	}

	public List<VendorDetailDate> getVendorDetailDateList() {
		return vendorDetailDateList;
	}

	public void setVendorDetailDateList(List<VendorDetailDate> vendorDetailDateList) {
		this.vendorDetailDateList = vendorDetailDateList;
	}

	public List<SecurityUserName> getUserName() {
		return userName;
	}

	public void setUserName(List<SecurityUserName> userName) {
		this.userName = userName;
	}

	public List<VendorCompanyType> getVendorCompanyTypeList() {
		return vendorCompanyTypeList;
	}

	public void setVendorCompanyTypeList(List<VendorCompanyType> vendorCompanyTypeList) {
		this.vendorCompanyTypeList = vendorCompanyTypeList;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	
	
}
