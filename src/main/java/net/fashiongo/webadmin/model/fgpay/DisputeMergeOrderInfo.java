/**
 * 
 */
package net.fashiongo.webadmin.model.fgpay;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * @author Brian
 *
 */
public class DisputeMergeOrderInfo {
	@Column(name = "OrderID")
	private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "PONumber")
	private String poNumber;

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
	@Column(name = "CheckOutDate")
	private LocalDateTime checkoutDate;

	public LocalDateTime getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDateTime checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
	@Transient
	private String checkOutDate;
	public String getCheckOutDate() {
		return (this.checkoutDate != null) ? this.checkoutDate.toString() : null;
	}
	
	@Column(name = "TotalAmountWSC")
	private BigDecimal totalAmountWSC;

	public BigDecimal getTotalAmountWSC() {
		return totalAmountWSC;
	}

	public void setTotalAmountWSC(BigDecimal totalAmountWSC) {
		this.totalAmountWSC = totalAmountWSC;
	}
}
