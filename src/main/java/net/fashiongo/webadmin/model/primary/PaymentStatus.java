package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "List_PaymentStatus")
public class PaymentStatus implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PaymentStatusID")
	@JsonProperty("PaymentStatusID")
	private Integer paymentStatusID;
	
	@Column(name = "PaymentStatus")
	@JsonProperty("PaymentStatus")
	private String paymentStatus;
	
	@Column(name = "PaymentStatusDescription")
	@JsonProperty("PaymentStatusDescription")
	private String paymentStatusDescription;
	
}
