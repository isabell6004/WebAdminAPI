package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "Order_PaymentStatus")
public class OrderPaymentStatusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderPaymentStatusID")
	private Integer orderPaymentStatusID;

	@Column(name = "ReferenceID")
	private int referenceID;

	@Column(name = "IsOrder")
	private int isOrder = 1;

	@Column(name = "PaymentStatusID")
	private int paymentStatusID;

	@Column(name = "PrePaymentStatusID")
	private Integer prePaymentStatusID;

	@Column(name = "RetailerID")
	private Integer retailerID;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;
}
