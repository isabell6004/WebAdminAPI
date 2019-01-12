package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeaturedVendorDaily {
	@JsonProperty("BuyerRate")
	@Column(name = "BuyerRate")
	private BigDecimal buyerRate;
	
	@JsonProperty("CheckOutAmount")
	@Column(name = "CheckOutAmount")
	private BigDecimal checkOutAmount;
	
	@JsonProperty("CheckOutQty")
	@Column(name = "CheckOutQty")
	private Integer checkOutQty;
	
	@JsonProperty("TotalADAmount")
	@Column(name = "TotalADAmount")
	private BigDecimal totalADAmount;
	
	@JsonProperty("VendorRate")
	@Column(name = "VendorRate")
	private BigDecimal vendorRate;
	
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	public BigDecimal getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(BigDecimal buyerRate) {
		this.buyerRate = buyerRate;
	}

	public BigDecimal getCheckOutAmount() {
		return checkOutAmount;
	}

	public void setCheckOutAmount(BigDecimal checkOutAmount) {
		this.checkOutAmount = checkOutAmount;
	}

	public Integer getCheckOutQty() {
		return checkOutQty;
	}

	public void setCheckOutQty(Integer checkOutQty) {
		this.checkOutQty = checkOutQty;
	}

	public BigDecimal getTotalADAmount() {
		return totalADAmount;
	}

	public void setTotalADAmount(BigDecimal totalADAmount) {
		this.totalADAmount = totalADAmount;
	}

	public BigDecimal getVendorRate() {
		return vendorRate;
	}

	public void setVendorRate(BigDecimal vendorRate) {
		this.vendorRate = vendorRate;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
}
