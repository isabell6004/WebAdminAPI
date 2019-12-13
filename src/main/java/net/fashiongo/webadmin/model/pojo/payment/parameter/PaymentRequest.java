package net.fashiongo.webadmin.model.pojo.payment.parameter;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequest {
	private BigDecimal amount;
	private Integer referenceId;
	private String referenceType;
	private String requestedBy;
}
