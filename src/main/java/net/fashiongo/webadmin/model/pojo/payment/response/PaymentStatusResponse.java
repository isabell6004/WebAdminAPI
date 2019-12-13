package net.fashiongo.webadmin.model.pojo.payment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentStatusResponse {
	private Integer cardStatusId;
	private Integer paymentStatusId;
	private String paymentStatusName;
}
