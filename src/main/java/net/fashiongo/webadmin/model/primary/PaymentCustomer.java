package net.fashiongo.webadmin.model.primary;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="PaymentCustomer")
public class PaymentCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentCustomerID")
	private Integer paymentCustomerId;

	@Column(name = "RetailerID")
	private Integer retailerId;

	@Column(name = "EmailAddress")
	private String emailAddress;

	@Column(name = "Description")
	private String description;

	@Column(name = "ReferenceID")
	private String referenceId;

	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;
}
