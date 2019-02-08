package net.fashiongo.webadmin.model.primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "Code_WholeSalerCompanyType")
public class VendorCompanyType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("CompanyTypeID")
	private Integer companyTypeID;
	
	@JsonProperty("CompanyTypeName")
	private String companyTypeName;
	
	@JsonProperty("Active")
	private Boolean active;
}
