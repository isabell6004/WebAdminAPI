/**
 * 
 */
package net.fashiongo.webadmin.model.fgpay;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.fashiongo.common.conversion.LocalDateTimeConverter;


/**
 * @author Brian
 *
 */
public class DisputeHeaderInfo {
	@Column(name = "DisputeID")
	private String disputeId;

	public String getDisputeId() {
		return disputeId;
	}

	public void setDisputeId(String disputeId) {
		this.disputeId = disputeId;
	}
	
	@Column(name = "DisputeAmount")
	private BigDecimal disputeAmount;

	public BigDecimal getDisputeAmount() {
		return disputeAmount;
	}

	public void setDisputeAmount(BigDecimal disputeAmount) {
		this.disputeAmount = disputeAmount;
	}
	
	@Column(name = "Currency")
	private String currency;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Column(name = "ChargeID")
	private String chargeId;

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	
	@Column(name = "RetailerCompanyName")
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name = "Detail")
	private String detail;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Column(name = "OrderAmount")
	private BigDecimal orderAmount;

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	@Column(name = "PONumber")
	private String poNumber;

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
	@JsonIgnore
	@Column(name = "CheckOutDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime checkOutDate;

	public LocalDateTime getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	@Transient
	private String checkoutDate;
	public String getCheckoutDate() {
		return (this.checkOutDate != null) ? this.checkOutDate.toString() : null;
	}
	
	@Column(name = "OrderID")
	private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "WholeSalerID")
	private Integer wholesalerId;

	public Integer getWholesalerId() {
		return wholesalerId;
	}

	public void setWholesalerId(Integer wholesalerId) {
		this.wholesalerId = wholesalerId;
	}
	
	@Column(name = "WholeCompanyName")
	private String vendorName;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	@Column(name = "RetailerID")
	private Integer retailerId;

	public Integer getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}
	
	@Column(name = "OrderSessionGUID")
	private String orderGUID;

	public String getOrderGUID() {
		return orderGUID;
	}

	public void setOrderGUID(String orderGUID) {
		this.orderGUID = orderGUID;
	}
}
