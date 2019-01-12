package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author DAHYE
 *
 */
@Entity
@Table(name = "Policy")
public class Policy {
	@Id
	@Column(name = "PolicyID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer policyID;
	
	@Column(name = "PolicyTitle")
	private String policyTitle;
	
	@Column(name = "PolicyContents")
	private String policyContents;
	
	@Column(name = "IsForVendor")
	private Boolean forVendor;
	
	@Column(name = "IsForRetailer")
	private Boolean forRetailer;
	
	@Column(name = "CreatedOn")
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "EffectiveOn")
	private LocalDateTime effectiveOn;
	
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "Active")
	private Boolean active;

	@JsonProperty("PolicyID")
	public Integer getPolicyID() {
		return policyID;
	}

	@JsonProperty("policyid")
	public void setPolicyID(Integer policyID) {
		this.policyID = policyID;
	}

	@JsonProperty("PolicyTitle")
	public String getPolicyTitle() {
		return policyTitle;
	}

	@JsonProperty("policytitle")
	public void setPolicyTitle(String policyTitle) {
		this.policyTitle = policyTitle;
	}

	@JsonProperty("PolicyContents")
	public String getPolicyContents() {
		return policyContents;
	}

	@JsonProperty("policycontents")
	public void setPolicyContents(String policyContents) {
		this.policyContents = policyContents;
	}

	@JsonProperty("IsForVendor")
	public Boolean getForVendor() {
		return forVendor;
	}

	@JsonProperty("isforvendor")
	public void setForVendor(Boolean forVendor) {
		this.forVendor = forVendor;
	}

	@JsonProperty("IsForRetailer")
	public Boolean getForRetailer() {
		return forRetailer;
	}

	@JsonProperty("isforretailer")
	public void setForRetailer(Boolean forRetailer) {
		this.forRetailer = forRetailer;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	

	public void setCreatedOn(String createdOn) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");	
		if(StringUtils.isNotEmpty(createdOn)) this.createdOn = LocalDateTime.parse(createdOn+" 00:00:00", formatter);
	}

	@JsonProperty("EffectiveOn")
	public LocalDateTime getEffectiveOn() {
		return effectiveOn;
	}

	@JsonProperty("effectiveon")
	public void setEffectiveOn(LocalDateTime effectiveOn) {
		this.effectiveOn = effectiveOn;
	}
	
	@JsonProperty("effectiveon")
	public void setEffectiveOn(String effectiveOn) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");	
		if(StringUtils.isNotEmpty(effectiveOn)) this.effectiveOn = LocalDateTime.parse(effectiveOn+" 00:00:00", formatter);
	}

	@JsonProperty("ModifiedOn")
	public String getModifiedOn() {
		if (modifiedOn == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedModifiedOn = modifiedOn.format(formatter);		
		return formattedModifiedOn;
	}

	@JsonProperty("modifiedon")
	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	@JsonProperty("modifiedon")
	public void setModifiedOn(String modifiedOn) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");	
		if(StringUtils.isNotEmpty(modifiedOn)) this.modifiedOn = LocalDateTime.parse(modifiedOn+" 00:00:00", formatter);
	}

	@JsonProperty("CreatedBy")
	public String getCreatedBy() {
		return createdBy;
	}

	@JsonProperty("createdby")
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@JsonProperty("ModifiedBy")
	public String getModifiedBy() {
		return modifiedBy;
	}

	@JsonProperty("modifiedby")
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@JsonProperty("Active")
	public Boolean getActive() {
		return BooleanUtils.isTrue(active) ? true : false;
	}

	@JsonProperty("active")
	public void setActive(Boolean active) {
		this.active = active;
	}

	
}
