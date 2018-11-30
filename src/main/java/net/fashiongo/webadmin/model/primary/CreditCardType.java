package net.fashiongo.webadmin.model.primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@Entity
@Table(name = "CreditCardType")
@Data
public class CreditCardType {
	@Id
	@JsonProperty("CreditCardTypeID")
	private Integer creditCardTypeID;
	
	@JsonProperty("CreditCardName")
	private String creditCardName;
	
	@JsonIgnore
	private boolean active;
}
