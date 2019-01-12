package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Photo_CreditUsage")
public class PhotoCreditUsage implements IPersistent, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CreditUsageID")
	private Integer creditUsageID;
	public Integer getCreditUsageID() {
		return creditUsageID;
	}

	public void setCreditUsageID(Integer creditUsageID) {
		this.creditUsageID = creditUsageID;
	}

	@Column(name = "PhotoCreditID")
	private String photoCreditID;
	public String getPhotoCreditID() {
		return photoCreditID;
	}

	public void setPhotoCreditID(String photoCreditID) {
		this.photoCreditID = photoCreditID;
	}

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	@Column(name = "IsCreditBack")
	private Boolean isCreditBack;
	public Boolean getIsCreditBack() {
		return isCreditBack;
	}

	public void setIsCreditBack(Boolean isCreditBack) {
		this.isCreditBack = isCreditBack;
	}

	@Column(name = "PhotoCreditUsedAmount")
	private BigDecimal photoCreditUsedAmount;
	public BigDecimal getPhotoCreditUsedAmount() {
		return photoCreditUsedAmount;
	}

	public void setPhotoCreditUsedAmount(BigDecimal photoCreditUsedAmount) {
		this.photoCreditUsedAmount = photoCreditUsedAmount;
	}

	@Column(name = "OrderID")
	private Integer orderID;
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	@Column(name = "PONumber")
	private String pONumber;
	public String getPONumber() {
		return pONumber;
	}

	public void setPONumber(String pONumber) {
		this.pONumber = pONumber;
	}

	@JsonIgnore
	@Column(name = "UsedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _usedOn;
	public LocalDateTime get_usedOn() {
		return _usedOn;
	}

	public void set_usedOn(LocalDateTime _usedOn) {
		this._usedOn = _usedOn;
	}

	@Transient
	private String usedOn;
	public String getUsedOn() {
		return _usedOn != null ? _usedOn.toString() : null;
	}
}