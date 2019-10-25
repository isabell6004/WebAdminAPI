package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "List_PaymentStatus")
public class ListPaymentStatusEntity {

	@Id
	@Column(name = "PaymentStatusID")
	private Integer paymentStatusID;

	@Column(name = "PaymentStatus")
	private String paymentStatus;

	@Column(name = "PaymentStatusDescription")
	private String paymentStatusDescription;
}
