package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class CategoryVendorInfo {
	@JsonProperty("CompanyName")
    private String companyName;
	
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
	@JsonProperty("CompanyTypeID")
    private Integer companyTypeID;
	
	@JsonProperty("SelectChk")
    private Integer selectChk;
	
	@JsonProperty("ViewChk")
    private Integer viewChk;
	
	@JsonProperty("BuyerRate")
	private BigDecimal buyerRate;
	
	@JsonProperty("VendorRate")
	private BigDecimal vendorRate;
	
	@JsonProperty("VendorTierGroup")
	private String vendorTierGroup;

	private Boolean isCM;
	private Boolean isPG;
	private Boolean isCS;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public Integer getCompanyTypeID() {
		return companyTypeID;
	}
	public void setCompanyTypeID(Integer companyTypeID) {
		this.companyTypeID = companyTypeID;
	}
	public Integer getSelectChk() {
		return selectChk;
	}
	public void setSelectChk(Integer selectChk) {
		this.selectChk = selectChk;
	}
	public Integer getViewChk() {
		return viewChk;
	}
	public void setViewChk(Integer viewChk) {
		this.viewChk = viewChk;
	}
	public BigDecimal getBuyerRate() {
		return buyerRate;
	}
	public void setBuyerRate(BigDecimal buyerRate) {
		this.buyerRate = buyerRate;
	}
	public BigDecimal getVendorRate() {
		return vendorRate;
	}
	public void setVendorRate(BigDecimal vendorRate) {
		this.vendorRate = vendorRate;
	}
	public String getVendorTierGroup() {
		return vendorTierGroup;
	}
	public void setVendorTierGroup(String vendorTierGroup) {
		this.vendorTierGroup = vendorTierGroup;
	}
	public Boolean getIsCM() {
		return isCM;
	}
	public void setIsCM(Boolean isCM) {
		this.isCM = isCM;
	}
	public Boolean getIsPG() {
		return isPG;
	}
	public void setIsPG(Boolean isPG) {
		this.isPG = isPG;
	}
	public Boolean getIsCS() {
		return isCS;
	}
	public void setIsCS(Boolean isCS) {
		this.isCS = isCS;
	}
}
