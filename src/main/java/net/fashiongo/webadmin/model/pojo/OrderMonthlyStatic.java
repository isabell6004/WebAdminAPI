package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class OrderMonthlyStatic {
	@Column(name = "DateYM")
	private Integer month;
	@Column(name = "NewAmount")
	private Double newAmount;
	@Column(name = "ReturnAmount")
	private Double returnAmount;
	
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Double getNewAmount() {
		return newAmount;
	}
	public void setNewAmount(Double newAmount) {
		this.newAmount = newAmount;
	}
	public Double getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}
	
}
