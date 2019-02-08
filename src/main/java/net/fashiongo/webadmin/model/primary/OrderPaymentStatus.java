package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Order_PaymentStatus")
public class OrderPaymentStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderPaymentStatusID;
	
	private Integer referenceID;
	
	private Integer isOrder;
	
	private Integer paymentStatusID;
	
	private Integer prePaymentStatusID;
	
	private Integer retailerID;
	
	private LocalDateTime createdOn;
	
	private String createdBy;
	
	private LocalDateTime modifiedOn;
	
	private String modifiedBy;
}
