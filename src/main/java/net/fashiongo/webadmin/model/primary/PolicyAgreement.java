package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

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
@Data
@Table(name = "PolicyAgreement")
public class PolicyAgreement {
	@Id
	@JsonProperty("PolicyAgreementID") 
	private Integer policyAgreementID; 
	
	@JsonProperty("PolicyID") 
	private Integer policyID; 
	
	@JsonProperty("WholeSalerID") 
	private Integer wholeSalerID; 
	
	@JsonProperty("CompanyName") 
	private String companyName; 
	
	@JsonProperty("RetailerID") 
	private Integer retailerID; 
	
	@JsonProperty("AgreedOn") 
	private LocalDateTime agreedOn; 
	
	@JsonProperty("AgreedByName") 
	private String agreedByName; 
	
	@JsonProperty("AgreedByID") 
	private String agreedByID; 
	
	@JsonProperty("IPAddress") 
	private String iPAddress; 
	
	@JsonProperty("Agreed") 
	private Boolean agreed;
	
}
