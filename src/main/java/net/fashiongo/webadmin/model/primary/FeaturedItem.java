package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "FeaturedItem")
public class FeaturedItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
//	@Basic(optional = false)
//	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("FeaturedItemID")
	@Column(name = "FeaturedItemID")
	private Integer featuredItemID;

	@Column(name = "FeaturedItemDate")
	private LocalDateTime FeaturedItemDate;
	@Column(name = "BestItemUse")
	private Integer BestItemUse;
	@Column(name = "WholeSalerID")
	private Integer WholeSalerID;
	@Column(name = "WholeSalerName")
	private String WholeSalerName;
	@Column(name = "ProductID")
	private Integer ProductID;
	@Column(name = "ProductName")
	private String ProductName;
	@Column(name = "CreatedOn")
	private LocalDateTime CreatedOn;
	@Column(name = "CreatedBy")
	private String CreatedBy;

	public Integer getFeaturedItemID() {
		return featuredItemID;
	}

	public void setFeaturedItemID(Integer featuredItemID) {
		this.featuredItemID = featuredItemID;
	}

	public LocalDateTime getFeaturedItemDate() {
		return FeaturedItemDate;
	}

	public void setFeaturedItemDate(LocalDateTime featuredItemDate) {
		FeaturedItemDate = featuredItemDate;
	}

	public Integer getBestItemUse() {
		return BestItemUse;
	}

	public void setBestItemUse(Integer bestItemUse) {
		BestItemUse = bestItemUse;
	}

	public Integer getWholeSalerID() {
		return WholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		WholeSalerID = wholeSalerID;
	}

	public String getWholeSalerName() {
		return WholeSalerName;
	}

	public void setWholeSalerName(String wholeSalerName) {
		WholeSalerName = wholeSalerName;
	}

	public Integer getProductID() {
		return ProductID;
	}

	public void setProductID(Integer productID) {
		ProductID = productID;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public LocalDateTime getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		CreatedOn = createdOn;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
