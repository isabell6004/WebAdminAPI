/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.common;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * @author Brian
 *
 */
public class SingleValueResult {
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
	
	@Column(name = "RecCnt")
	private Integer recCnt;
	public Integer getRecCnt(){
		return recCnt;
	}
	public void setRecCnt(Integer recCnt){
		this.recCnt = recCnt;
	}
}
