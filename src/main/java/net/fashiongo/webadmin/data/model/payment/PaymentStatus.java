package net.fashiongo.webadmin.data.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PaymentStatus {

	@JsonProperty("PaymentStatusID")
	private Integer paymentStatusID;

	@JsonProperty("PaymentStatus")
	private String paymentStatus;

	@JsonProperty("PaymentStatusDescription")
	private String paymentStatusDescription;

	public PaymentStatus(Integer paymentStatusID, String paymentStatus, String paymentStatusDescription) {
		this.paymentStatusID = paymentStatusID;
		this.paymentStatus = paymentStatus;
		this.paymentStatusDescription = paymentStatusDescription;
	}
}
