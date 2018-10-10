package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class OrderMonthlyStatic {
	@Column(name = "DateYM")
	private String month;
	@Column(name = "NewAmount")
	private BigDecimal newAmount;
	@Column(name = "ReturnAmount")
	private BigDecimal returnAmount;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getNewAmount() {
		return newAmount;
	}
	public void setNewAmount(BigDecimal newAmount) {
		this.newAmount = newAmount;
	}
	public BigDecimal getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}
	
}
