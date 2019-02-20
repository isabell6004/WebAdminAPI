package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "tblRetailer")
public class RetailerCompany {
	@Id
	@JsonProperty("RetailerID")
	@Column(name = "RetailerID")
	private Integer retailerID;
	
	@JsonProperty("CompanyName")
	@Column(name = "CompanyName")
	private String companyName;
	
	@JsonProperty("FirstName")
	@Column(name = "FirstName")
	private String firstName;
	
	@JsonProperty("LastName")
	@Column(name = "LastName")
	private String lastName;
	
	@JsonProperty("EMail")
	@Column(name = "EMail")
	private String eMail;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private String active;
	
	@JsonIgnore
	@Column(name = "IsOperatorRead")
	private Boolean operatorRead;

	public Integer getRetailerID() {
		return retailerID;
	}

	public void setRetailerID(Integer retailerID) {
		this.retailerID = retailerID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Boolean getOperatorRead() {
		return operatorRead;
	}

	public void setOperatorRead(Boolean operatorRead) {
		this.operatorRead = operatorRead;
	}
	
	
	
}
