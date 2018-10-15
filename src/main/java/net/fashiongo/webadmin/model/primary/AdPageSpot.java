package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "Ad_PageSpot")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "AdPageSpot.findAll", query = "SELECT a FROM AdPageSpot a"),
		@NamedQuery(name = "AdPageSpot.findBySpotID", query = "SELECT a FROM AdPageSpot a WHERE a.spotID = :spotID"),
		@NamedQuery(name = "AdPageSpot.findByPageID", query = "SELECT a FROM AdPageSpot a WHERE a.pageID = :pageID"),
		@NamedQuery(name = "AdPageSpot.findByCategoryID", query = "SELECT a FROM AdPageSpot a WHERE a.categoryID = :categoryID"),
		@NamedQuery(name = "AdPageSpot.findByBodySizeID", query = "SELECT a FROM AdPageSpot a WHERE a.bodySizeID = :bodySizeID"),
		@NamedQuery(name = "AdPageSpot.findBySpotName", query = "SELECT a FROM AdPageSpot a WHERE a.spotName = :spotName"),
		@NamedQuery(name = "AdPageSpot.findBySpotDescription", query = "SELECT a FROM AdPageSpot a WHERE a.spotDescription = :spotDescription"),
		@NamedQuery(name = "AdPageSpot.findByPrice1", query = "SELECT a FROM AdPageSpot a WHERE a.price1 = :price1"),
		@NamedQuery(name = "AdPageSpot.findByPrice2", query = "SELECT a FROM AdPageSpot a WHERE a.price2 = :price2"),
		@NamedQuery(name = "AdPageSpot.findByPrice3", query = "SELECT a FROM AdPageSpot a WHERE a.price3 = :price3"),
		@NamedQuery(name = "AdPageSpot.findByPrice4", query = "SELECT a FROM AdPageSpot a WHERE a.price4 = :price4"),
		@NamedQuery(name = "AdPageSpot.findByPrice5", query = "SELECT a FROM AdPageSpot a WHERE a.price5 = :price5"),
		@NamedQuery(name = "AdPageSpot.findByPrice6", query = "SELECT a FROM AdPageSpot a WHERE a.price6 = :price6"),
		@NamedQuery(name = "AdPageSpot.findByPrice7", query = "SELECT a FROM AdPageSpot a WHERE a.price7 = :price7"),
		@NamedQuery(name = "AdPageSpot.findByActive", query = "SELECT a FROM AdPageSpot a WHERE a.active = :active"),
		@NamedQuery(name = "AdPageSpot.findByIncludeVendorCategory", query = "SELECT a FROM AdPageSpot a WHERE a.includeVendorCategory = :includeVendorCategory"),
		@NamedQuery(name = "AdPageSpot.findBySpotInstanceCount", query = "SELECT a FROM AdPageSpot a WHERE a.spotInstanceCount = :spotInstanceCount"),
		@NamedQuery(name = "AdPageSpot.findByBannerImage", query = "SELECT a FROM AdPageSpot a WHERE a.bannerImage = :bannerImage"),
		@NamedQuery(name = "AdPageSpot.findByCreatedOn", query = "SELECT a FROM AdPageSpot a WHERE a.createdOn = :createdOn"),
		@NamedQuery(name = "AdPageSpot.findByCreatedBy", query = "SELECT a FROM AdPageSpot a WHERE a.createdBy = :createdBy"),
		@NamedQuery(name = "AdPageSpot.findByModifiedOn", query = "SELECT a FROM AdPageSpot a WHERE a.modifiedOn = :modifiedOn"),
		@NamedQuery(name = "AdPageSpot.findByModifiedBy", query = "SELECT a FROM AdPageSpot a WHERE a.modifiedBy = :modifiedBy"),
		@NamedQuery(name = "AdPageSpot.findByBidEffectiveOn", query = "SELECT a FROM AdPageSpot a WHERE a.bidEffectiveOn = :bidEffectiveOn"),
		@NamedQuery(name = "AdPageSpot.findBySpotItemCount", query = "SELECT a FROM AdPageSpot a WHERE a.spotItemCount = :spotItemCount"),
		@NamedQuery(name = "AdPageSpot.findByMaxPurchasable", query = "SELECT a FROM AdPageSpot a WHERE a.maxPurchasable = :maxPurchasable") })
public class AdPageSpot implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "SpotID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("SpotID")
	private Integer spotID;

	@Basic(optional = false)
	@NotNull
	@Column(name = "PageID")
	@JsonProperty("PageID")
	private int pageID;

	@Column(name = "CategoryID")
	@JsonProperty("CategoryID")
	private Integer categoryID;

	@Column(name = "BodySizeID")
	@JsonProperty("BodySizeID")
	private Integer bodySizeID;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "SpotName")
	@JsonProperty("SpotName")
	private String spotName;

	@Size(max = 2147483647)
	@Column(name = "SpotDescription")
	@JsonProperty("SpotDescription")
	private String spotDescription;

	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "Price1")
	@JsonProperty("Price1")
	private BigDecimal price1;

	@Column(name = "Price2")
	@JsonProperty("Price2")
	private BigDecimal price2;

	@Column(name = "Price3")
	@JsonProperty("Price3")
	private BigDecimal price3;

	@Column(name = "Price4")
	@JsonProperty("Price4")
	private BigDecimal price4;

	@Column(name = "Price5")
	@JsonProperty("Price5")
	private BigDecimal price5;

	@Column(name = "Price6")
	@JsonProperty("Price6")
	private BigDecimal price6;

	@Column(name = "Price7")
	@JsonProperty("Price7")
	private BigDecimal price7;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Active")
	@JsonProperty("Active")
	private boolean active;

	@Column(name = "IncludeVendorCategory")
	@JsonProperty("IncludeVendorCategory")
	private Boolean includeVendorCategory;

	@Basic(optional = false)
	@NotNull
	@Column(name = "SpotInstanceCount")
	@JsonProperty("SpotInstanceCount")
	private int spotInstanceCount;

	@Size(max = 100)
	@Column(name = "BannerImage")
	@JsonProperty("BannerImage")
	private String bannerImage;

	@Column(name = "CreatedOn")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("CreatedOn")
	private Date createdOn;

	@Size(max = 50)
	@Column(name = "CreatedBy")
	@JsonProperty("CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("ModifiedOn")
	private Date modifiedOn;

	@Size(max = 50)
	@Column(name = "ModifiedBy")
	@JsonProperty("ModifiedBy")
	private String modifiedBy;

	@Column(name = "BidEffectiveOn")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("BidEffectiveOn")
	private Date bidEffectiveOn;

	@Column(name = "SpotItemCount")
	@JsonProperty("SpotItemCount")
	private Integer spotItemCount;

	@Column(name = "MaxPurchasable")
	@JsonProperty("MaxPurchasable")
	private Integer maxPurchasable;

	public AdPageSpot() {
	}

	public AdPageSpot(Integer spotID) {
		this.spotID = spotID;
	}

	public AdPageSpot(Integer spotID, int pageID, String spotName, boolean active, int spotInstanceCount) {
		this.spotID = spotID;
		this.pageID = pageID;
		this.spotName = spotName;
		this.active = active;
		this.spotInstanceCount = spotInstanceCount;
	}

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
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

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Boolean getIncludeVendorCategory() {
		return includeVendorCategory;
	}

	public void setIncludeVendorCategory(Boolean includeVendorCategory) {
		this.includeVendorCategory = includeVendorCategory;
	}

	public int getSpotInstanceCount() {
		return spotInstanceCount;
	}

	public void setSpotInstanceCount(int spotInstanceCount) {
		this.spotInstanceCount = spotInstanceCount;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getBidEffectiveOn() {
		return bidEffectiveOn;
	}

	public void setBidEffectiveOn(Date bidEffectiveOn) {
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (spotID != null ? spotID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof AdPageSpot)) {
			return false;
		}
		AdPageSpot other = (AdPageSpot) object;
		if ((this.spotID == null && other.spotID != null)
				|| (this.spotID != null && !this.spotID.equals(other.spotID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.mavenproject3.AdPageSpot[ spotID=" + spotID + " ]";
	}

}
