package net.fashiongo.webadmin.model.pojo.admin;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @author Reo
 *
 */
public class AspnetUserRoles {
	@Column(name = "TotalCount")
	private Integer totalCount;
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
