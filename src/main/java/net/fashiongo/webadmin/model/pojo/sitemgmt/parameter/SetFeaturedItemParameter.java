package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetFeaturedItemParameter {
	@JsonProperty("settype")
	private String setType;
	
	@JsonProperty("ProductID")
	private String productID;
	
	@JsonProperty("WholeSalerID")
	private String wholeSalerID;
	
	@JsonProperty("CompanyName")
	private String companyName;
	
	@JsonProperty("ProductName")
	private String productName;
	
	@JsonProperty("fromdate")
	private String fromDate;

	public String getSetType() {		
		return StringUtils.isEmpty(setType) ? null : setType;
	}

	public void setSetType(String setType) {
		this.setType = setType;
	}

	public Integer getProductID() {
		return StringUtils.isEmpty(productID) ? 0 : Integer.parseInt(productID);
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public Integer getWholeSalerID() {
		return StringUtils.isEmpty(wholeSalerID) ? 0 : Integer.parseInt(wholeSalerID);
	}

	public void setWholeSalerID(String wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public String getCompanyName() {
		return StringUtils.isEmpty(companyName) ? null : companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProductName() {
		return StringUtils.isEmpty(productName) ? null : productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public LocalDateTime getFromDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return StringUtils.isEmpty(fromDate) ? null : LocalDate.parse(fromDate, formatter).atStartOfDay();
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
}
