package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "PaymentTransaction")
public class PaymentTransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransactionID")
	private Integer transactionID;

	@Column(name = "TransactionType")
	private int transactionType;

	@Column(name = "ReferenceID")
	private Integer referenceID;

	@Column(name = "ReferenceTypeID")
	private Integer referenceTypeID;

	@Column(name = "PGReferenceID")
	private String pgReferenceID;

	@Column(name = "CreditCardID")
	private int creditCardID;

	@Column(name = "CreditCardReferenceID")
	private String creditCardReferenceID;

	@Column(name = "Amount")
	private BigDecimal amount;

	@Column(name = "Success")
	private boolean success;

	@Column(name = "ReasonCode")
	private int reasonCode;

	@Column(name = "Detail")
	private String detail;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "DeclineCode")
	private String declineCode;

	@Column(name = "IdempotencyKey")
	private String idempotencyKey;

	@Column(name = "IdempotencyKeyCreatedOn")
	private Timestamp idempotencyKeyCreatedOn;

	@Column(name = "RequestTime")
	private Timestamp requestTime;

	@Column(name = "ResponseTime")
	private Timestamp responseTime;

	@Column(name = "Card_Funding")
	private String card_Funding;

	@Column(name = "Card_Brand")
	private String card_Brand;

	@Column(name = "Card_Last4")
	private String card_Last4;
}
