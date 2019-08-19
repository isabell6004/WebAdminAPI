package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PolicyAgreement {

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

	public PolicyAgreement(Integer policyAgreementID, Integer policyID, Integer wholeSalerID, String companyName, Integer retailerID, Timestamp agreedOn, String agreedByName, String agreedByID, String iPAddress, Boolean agreed) {
		this.policyAgreementID = policyAgreementID;
		this.policyID = policyID;
		this.wholeSalerID = wholeSalerID;
		this.companyName = companyName;
		this.retailerID = retailerID;
		this.agreedOn = agreedOn != null ? agreedOn.toLocalDateTime() : null;
		this.agreedByName = agreedByName;
		this.agreedByID = agreedByID;
		this.iPAddress = iPAddress;
		this.agreed = agreed;
	}
}
