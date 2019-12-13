package net.fashiongo.webadmin.model.pojo.payment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class PaymentTransactionResponse {
	private Integer transactionId;
	private int transactionType;
	private Integer creditCardId;
	private BigDecimal amount;
	private BigDecimal authCaptureAmount;
	private boolean success;
	private int reasonCode;
	private String detail;
	private LocalDateTime createdOn;
	private String createdBy;

	public String getTransactionTypeName() {
		if (transactionType == 3 /* 3 = Auth & Capture */) {
			return "Auth & Capture";
		} else if (transactionType == 5 /* 5 = Refund */ && amount != null && authCaptureAmount != null) {
			int compare = amount.compareTo(authCaptureAmount);
			if (compare == 0) return "Full Refund";
			else if (compare < 0) return "Partial Refund";
		}
		return "";
	}
}
