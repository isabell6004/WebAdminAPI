package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.common.conversion.LocalDateTimeConverter;

/**
 *
 * @author jiwon Kim
 */
@Entity
@Table(name = "CollectionCategoryItem")
public class CollectionCategoryItem implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("CollectionCategoryItemID")
	@Column(name = "CollectionCategoryItemID")
	private Integer collectionCategoryItemID;

	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private Integer spotID;

	@JsonProperty("FromDate")
	@Column(name = "FromDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime fromDate;

	@JsonProperty("CollectionCategoryID")
	@Column(name = "CollectionCategoryID")
	private Integer collectionCategoryID;

	@JsonProperty("ProductID")
	@Column(name = "ProductID")
	private Integer productID;
	
	@JsonProperty("CreatedOn")
	@Column(name = "CreatedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy")
	@Column(name = "CreatedBy")
	private String createdBy;

	@JsonProperty("CollectionCategoryType")
	@Column(name = "CollectionCategoryType")
	private Integer collectionCategoryType;

	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	public Integer getCollectionCategoryItemID() {
		return collectionCategoryItemID;
	}

	public void setCollectionCategoryItemID(Integer collectionCategoryItemID) {
		this.collectionCategoryItemID = collectionCategoryItemID;
	}

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public Integer getCollectionCategoryID() {
		return collectionCategoryID;
	}

	public void setCollectionCategoryID(Integer collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getCollectionCategoryType() {
		return collectionCategoryType;
	}

	public void setCollectionCategoryType(Integer collectionCategoryType) {
		this.collectionCategoryType = collectionCategoryType;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	
	
}
