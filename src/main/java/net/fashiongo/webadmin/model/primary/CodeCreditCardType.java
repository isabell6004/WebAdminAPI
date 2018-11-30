package net.fashiongo.webadmin.model.primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@Entity
@Table(name = "Code_CreditCardType")
@Data
public class CodeCreditCardType {
	@Id
	@JsonProperty("CreditCardTypeID")
	private Integer creditCardTypeID;
	
	@JsonProperty("CreditCardType")
	private String creditCardType;
}
