package net.fashiongo.webadmin.data.model.order;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ConsolidationOrderSummary {

	private Integer consolidationId;

	private BigDecimal sumTotalAmount;

	private Integer sumTotalQty;

	private Long orderCount;

	public ConsolidationOrderSummary(Integer consolidationId, BigDecimal sumTotalAmount, Integer sumTotalQty, Long orderCount) {
		this.consolidationId = consolidationId;
		this.sumTotalAmount = sumTotalAmount;
		this.sumTotalQty = sumTotalQty;
		this.orderCount = orderCount;
	}
}

