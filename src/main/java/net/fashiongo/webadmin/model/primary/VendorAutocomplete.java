package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Andy
 *
 */
@Entity
@Table(name = "tblWholeSaler")
@Where(clause = "Active = 1 and ShopActive = 1")
public class VendorAutocomplete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WholeSalerID")
	private Integer wholeSalerId;
	public Integer getWholeSalerId() {
		return wholeSalerId;
	}
	public void setWholeSalerId(Integer wholeSalerId) {
		this.wholeSalerId = wholeSalerId;
	}

	@JsonProperty("CompanyName")
	@Column(name = "CompanyName")
	private String companyName;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "FirstName")
	private String FirstName;
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	@Column(name = "LastName")
	private String lastName;
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "Email")
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Phone")
	private String phoneNumber;
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
