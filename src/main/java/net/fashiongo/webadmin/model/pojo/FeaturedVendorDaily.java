package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeaturedVendorDaily {
	@JsonProperty("FeaturedItemID")
	@Column(name = "BuyerRate")
	private BigDecimal buyerRate;
	
	@JsonProperty("FeaturedItemID")
	@Column(name = "CheckOutAmount")
	private BigDecimal checkOutAmount;
	
	@JsonProperty("FeaturedItemID")
	@Column(name = "CheckOutQty")
	private BigDecimal checkOutQty;
	
	@JsonProperty("FeaturedItemID")
	@Column(name = "TotalADAmount")
	private BigDecimal totalADAmount;
	
	@JsonProperty("FeaturedItemID")
	@Column(name = "VendorRate")
	private BigDecimal vendorRate;
	
	@JsonProperty("FeaturedItemID")
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

	public BigDecimal getCheckOutQty() {
		return checkOutQty;
	}

	public void setCheckOutQty(BigDecimal checkOutQty) {
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
