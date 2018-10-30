package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Policy")
public class Policy {
	@Id
	@Column(name = "PolicyID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("PolicyID")
	private Integer policyID;
	
	@Column(name = "PolicyTitle")
	@JsonProperty("PolicyTitle")
	private String policyTitle;
	
	@Column(name = "PolicyContents")
	@JsonProperty("PolicyContents")
	private String policyContents;
	
	@Column(name = "IsForVendor")
	@JsonProperty("IsForVendor")
	private Boolean forVendor;
	
	@Column(name = "IsForRetailer")
	@JsonProperty("IsForRetailer")
	private Boolean forRetailer;
	
	@Column(name = "CreatedOn")
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "EffectiveOn")
	@JsonProperty("EffectiveOn")
	private LocalDateTime effectiveOn;
	
	@Column(name = "ModifiedOn")
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@Column(name = "CreatedBy")
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@Column(name = "ModifiedBy")
	@JsonProperty("ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private Boolean active;

	public Integer getPolicyID() {
		return policyID;
	}

	public String getPolicyTitle() {
		return policyTitle;
	}

	public String getPolicyContents() {
		return policyContents;
	}

	public Boolean getForVendor() {
		return forVendor;
	}

	public Boolean getForRetailer() {
		return forRetailer;
	}

	public String getCreatedOn() {
		if (createdOn == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedCreatedOn = createdOn.format(formatter);		
		return formattedCreatedOn;
	}

	public String getEffectiveOn() {
		if (effectiveOn == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedEeffectiveOn = effectiveOn.format(formatter);		
		return formattedEeffectiveOn;
	}
	
	public String getModifiedOn() {
		if (modifiedOn == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedModifiedOn = modifiedOn.format(formatter);		
		return formattedModifiedOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public Boolean getActive() {
		return active;
	}

	public void setPolicyID(Integer policyID) {
		this.policyID = policyID;
	}

	public void setPolicyTitle(String policyTitle) {
		this.policyTitle = policyTitle;
	}

	public void setPolicyContents(String policyContents) {
		this.policyContents = policyContents;
	}

	public void setForVendor(Boolean forVendor) {
		this.forVendor = forVendor;
	}

	public void setForRetailer(Boolean forRetailer) {
		this.forRetailer = forRetailer;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public void setEffectiveOn(LocalDateTime effectiveOn) {
		this.effectiveOn = effectiveOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
}
