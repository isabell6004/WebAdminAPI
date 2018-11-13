package net.fashiongo.webadmin.model.pojo.ad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author Jiwon Kim
 */
public class VendorData2 {


	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("VendorRate")
	@Column(name = "VendorRate")
	private BigDecimal vendorRate;
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
	
	
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public BigDecimal getVendorRate() {
		return vendorRate;
	}
	public void setVendorRate(BigDecimal vendorRate) {
		this.vendorRate = vendorRate;
	}
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
	
}