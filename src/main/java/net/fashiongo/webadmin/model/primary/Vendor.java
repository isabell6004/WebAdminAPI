package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author roy
 */
@Data
@Entity
@Table(name = "tblWholeSaler")
public class Vendor {
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
	
	@JsonIgnore
	@Column(name = "OrderActive")
	private Boolean orderActive;
	
	@JsonIgnore
	@Column(name="Vendor_Type")
	private Integer vendorType;

    @Column(name = "Phone")
    private String phone;
}
