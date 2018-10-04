package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "Ad_PageSpot")
public class AdPageSpot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private Integer spotID;
	
	@JsonProperty("PageID")
	@Column(name = "PageID")
	private Integer pageID;
	
	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;
	
	@JsonProperty("BodySizeID")
	@Column(name = "BodySizeID")
	private Integer bodySizeID;
	
	@JsonProperty("SpotName")
	@Column(name = "SpotName")
	private String spotName;
	
	@JsonProperty("SpotDescription")
	@Column(name = "SpotDescription")
	private String spotDescription;
	
	@JsonProperty("Price1")
	@Column(name = "Price1")
	private BigDecimal price1;
	
	@JsonProperty("Price2")
	@Column(name = "Price2")
	private BigDecimal price2;
	
	@JsonProperty("Price3")
	@Column(name = "Price3")
	private BigDecimal price3;
	
	@JsonProperty("Price4")
	@Column(name = "Price4")
	private BigDecimal price4;
	
	@JsonProperty("Price5")
	@Column(name = "Price5")
	private BigDecimal price5;
	
	@JsonProperty("Price6")
	@Column(name = "Price6")
	private BigDecimal price6;
	
	@JsonProperty("Price7")
	@Column(name = "Price7")
	private BigDecimal price7;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;
	
	@JsonProperty("IncludeVendorCategory")
	@Column(name = "IncludeVendorCategory")
	private Boolean includeVendorCategory;
	
	@JsonProperty("SpotInstanceCount")
	@Column(name = "SpotInstanceCount")
	private Integer spotInstanceCount;
	
	@JsonProperty("BannerImage")
	@Column(name = "BannerImage")
	private String bannerImage;
	
	@JsonProperty("CreatedOn")
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy")
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@JsonProperty("ModifiedOn")
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@JsonProperty("ModifiedBy")
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@JsonProperty("BidEffectiveOn")
	@Column(name = "BidEffectiveOn")
	private LocalDateTime bidEffectiveOn;
	
	@JsonProperty("SpotItemCount")
	@Column(name = "SpotItemCount")
	private Integer spotItemCount;
	
	@JsonProperty("MaxPurchasable")
	@Column(name = "MaxPurchasable")
	private Integer maxPurchasable;

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}

	public Integer getPageID() {
		return pageID;
	}

	public void setPageID(Integer pageID) {
		this.pageID = pageID;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getBodySizeID() {
		return bodySizeID;
	}

	public void setBodySizeID(Integer bodySizeID) {
		this.bodySizeID = bodySizeID;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public String getSpotDescription() {
		return spotDescription;
	}

	public void setSpotDescription(String spotDescription) {
		this.spotDescription = spotDescription;
	}

	public BigDecimal getPrice1() {
		return price1;
	}

	public void setPrice1(BigDecimal price1) {
		this.price1 = price1;
	}

	public BigDecimal getPrice2() {
		return price2;
	}

	public void setPrice2(BigDecimal price2) {
		this.price2 = price2;
	}

	public BigDecimal getPrice3() {
		return price3;
	}

	public void setPrice3(BigDecimal price3) {
		this.price3 = price3;
	}

	public BigDecimal getPrice4() {
		return price4;
	}

	public void setPrice4(BigDecimal price4) {
		this.price4 = price4;
	}

	public BigDecimal getPrice5() {
		return price5;
	}

	public void setPrice5(BigDecimal price5) {
		this.price5 = price5;
	}

	public BigDecimal getPrice6() {
		return price6;
	}

	public void setPrice6(BigDecimal price6) {
		this.price6 = price6;
	}

	public BigDecimal getPrice7() {
		return price7;
	}

	public void setPrice7(BigDecimal price7) {
		this.price7 = price7;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIncludeVendorCategory() {
		return includeVendorCategory;
	}

	public void setIncludeVendorCategory(Boolean includeVendorCategory) {
		this.includeVendorCategory = includeVendorCategory;
	}

	public Integer getSpotInstanceCount() {
		return spotInstanceCount;
	}

	public void setSpotInstanceCount(Integer spotInstanceCount) {
		this.spotInstanceCount = spotInstanceCount;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
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

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getBidEffectiveOn() {
		return bidEffectiveOn;
	}

	public void setBidEffectiveOn(LocalDateTime bidEffectiveOn) {
		this.bidEffectiveOn = bidEffectiveOn;
	}

	public Integer getSpotItemCount() {
		return spotItemCount;
	}

	public void setSpotItemCount(Integer spotItemCount) {
		this.spotItemCount = spotItemCount;
	}

	public Integer getMaxPurchasable() {
		return maxPurchasable;
	}

	public void setMaxPurchasable(Integer maxPurchasable) {
		this.maxPurchasable = maxPurchasable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
