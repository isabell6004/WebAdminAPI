package net.fashiongo.webadmin.model.pojo.payment.response;

import javax.persistence.Column;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentRecoveryResponse {
	
	@Column(name = "Code")
	private Boolean code;
	
	@Column(name = "Message")
	private String message;
}
