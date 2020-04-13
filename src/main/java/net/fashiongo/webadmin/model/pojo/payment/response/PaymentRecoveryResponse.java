package net.fashiongo.webadmin.model.pojo.payment.response;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRecoveryResponse {
	
	@Column(name = "Code")
	private int code;
	
	@Column(name = "Message")
	private String message;
}
