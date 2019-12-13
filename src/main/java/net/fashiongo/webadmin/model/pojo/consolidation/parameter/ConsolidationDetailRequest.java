package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConsolidationDetailRequest {
	private Integer consolidationId;
	private Integer shipMethodId;
	private Boolean isShipped;
	private String shippedOn;
	private BigDecimal shippingCharge;
	private BigDecimal actualShippingCharge;
	private String trackingNumber;
	private String inHouseMemo;
	private BigDecimal waivedAmountByFg;
}
