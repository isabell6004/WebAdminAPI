package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class CreditCard {

	@JsonProperty("IsDefaultCard")
	private boolean isDefaultCard;

	@JsonProperty("CreditCardID")
	private Integer creditCardID;

	@JsonProperty("CreditCardTypeID")
	private Integer creditCardTypeID;

	@JsonProperty("CreditCardType")
	private String creditCardType;

	@JsonProperty("Last4Digit")
	private String last4Digit;

	public boolean getIsDefaultCard() {
		return isDefaultCard;
	}

	public Integer getCreditCardID() {
		return creditCardID;
	}

	public Integer getCreditCardTypeID() {
		return creditCardTypeID;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public String getLast4Digit() {
		return last4Digit;
	}

	public CreditCard(boolean isDefaultCard, Integer creditCardID, Integer creditCardTypeID, String creditCardType, String last4Digit) {
		this.isDefaultCard = isDefaultCard;
		this.creditCardID = creditCardID;
		this.creditCardTypeID = creditCardTypeID;
		this.creditCardType = creditCardType;
		this.last4Digit = last4Digit;
	}
}
