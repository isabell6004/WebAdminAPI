package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "Code_PaymentMethod")
public class CodePaymentMethodEntity {

	@Id
	@Column(name = "PaymentMethodID")
	private Integer paymentMethodID;

	@Column(name = "PaymentMethodName")
	private String paymentMethodName;

	@Column(name = "PaymentMethodDescription")
	private String paymentMethodDescription;

	@Column(name = "PaymentCode")
	private String paymentCode;

	@Column(name = "Active")
	private boolean active;
}
