package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author DAHYE
 *
 */
public class PolicyDetail {
	@JsonProperty("PolicyAgreementID") 
	private String policyAgreementID; 
	@JsonProperty("PolicyID") 
	private String policyID; 
	@JsonProperty("WholeSalerID") 
	private String wholeSalerID; 
	@JsonProperty("CompanyName") 
	private String companyName; 
	@JsonProperty("RetailerID") 
	private String retailerID; 
	@JsonProperty("AgreedOn") 
	private String agreedOn; 
	@JsonProperty("AgreedByName") 
	private String agreedByName; 
	@JsonProperty("AgreedByID") 
	private String agreedByID; 
	@JsonProperty("IPAddress") 
	private String iPAddress; 
	@JsonProperty("Agreed") 
	private String agreed;
	public String getPolicyAgreementID() {
		return policyAgreementID;
	}
	public void setPolicyAgreementID(String policyAgreementID) {
		this.policyAgreementID = policyAgreementID;
	}
	public String getPolicyID() {
		return policyID;
	}
	public void setPolicyID(String policyID) {
		this.policyID = policyID;
	}
	public String getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(String wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRetailerID() {
		return retailerID;
	}
	public void setRetailerID(String retailerID) {
		this.retailerID = retailerID;
	}
	public String getAgreedOn() {
		return agreedOn;
	}
	public void setAgreedOn(String agreedOn) {
		this.agreedOn = agreedOn;
	}
	public String getAgreedByName() {
		return agreedByName;
	}
	public void setAgreedByName(String agreedByName) {
		this.agreedByName = agreedByName;
	}
	public String getAgreedByID() {
		return agreedByID;
	}
	public void setAgreedByID(String agreedByID) {
		this.agreedByID = agreedByID;
	}
	public String getiPAddress() {
		return iPAddress;
	}
	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	public String getAgreed() {
		return agreed;
	}
	public void setAgreed(String agreed) {
		this.agreed = agreed;
	}

	
}
