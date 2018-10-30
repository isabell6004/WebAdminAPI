package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author roy
 */
@Entity
@Table(name = "tblWholeSaler")
public class VendorCompany implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerId;
	
	@JsonProperty("CompanyName")
	@Column(name = "CompanyName")
	private String companyName;
	
	@JsonIgnore
	@Column(name = "Active")
	private Boolean active;
	
	@JsonIgnore
	@Column(name = "ShopActive")
	private Boolean shopActive;

	public Integer getWholeSalerId() {
		return wholeSalerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setWholeSalerId(Integer wholeSalerId) {
		this.wholeSalerId = wholeSalerId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Boolean getActive() {
		return active;
	}

	public Boolean getShopActive() {
		return shopActive;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setShopActive(Boolean shopActive) {
		this.shopActive = shopActive;
	}
}
