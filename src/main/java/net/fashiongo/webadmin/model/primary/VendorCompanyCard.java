package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "tblWholeSaler")
public class VendorCompanyCard implements Serializable {
	@Id
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerId;
	
	@JsonProperty("ChargedByCreditCard")
	@Column(name = "ChargedByCreditCard")
	private Boolean chargedByCreditCard;
	
	@JsonProperty("WholeSalerGUID")
	@Column(name = "WholeSalerGUID")
	private String wholeSalerGUID;

	
	public Integer getWholeSalerId() {
		return wholeSalerId;
	}

	public void setWholeSalerId(Integer wholeSalerId) {
		this.wholeSalerId = wholeSalerId;
	}

	public Boolean getChargedByCreditCard() {
		return chargedByCreditCard;
	}

	public void setChargedByCreditCard(Boolean chargedByCreditCard) {
		this.chargedByCreditCard = chargedByCreditCard;
	}

	public String getWholeSalerGUID() {
		return wholeSalerGUID;
	}

	public void setWholeSalerGUID(String wholeSalerGUID) {
		this.wholeSalerGUID = wholeSalerGUID;
	}
	
	
}
