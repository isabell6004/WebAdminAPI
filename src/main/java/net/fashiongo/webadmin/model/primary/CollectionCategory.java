package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "CollectionCategory")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "CollectionCategory.findAll", query = "SELECT c FROM CollectionCategory c"),
		@NamedQuery(name = "CollectionCategory.findByCollectionCategoryID", query = "SELECT c FROM CollectionCategory c WHERE c.collectionCategoryID = :collectionCategoryID"),
		@NamedQuery(name = "CollectionCategory.findByCollectionCategoryName", query = "SELECT c FROM CollectionCategory c WHERE c.collectionCategoryName = :collectionCategoryName"),
		@NamedQuery(name = "CollectionCategory.findByParentCollectionCategoryID", query = "SELECT c FROM CollectionCategory c WHERE c.parentCollectionCategoryID = :parentCollectionCategoryID"),
		@NamedQuery(name = "CollectionCategory.findBySpotID", query = "SELECT c FROM CollectionCategory c WHERE c.spotID = :spotID"),
		@NamedQuery(name = "CollectionCategory.findByLvl", query = "SELECT c FROM CollectionCategory c WHERE c.lvl = :lvl"),
		@NamedQuery(name = "CollectionCategory.findByListOrder", query = "SELECT c FROM CollectionCategory c WHERE c.listOrder = :listOrder"),
		@NamedQuery(name = "CollectionCategory.findByActive", query = "SELECT c FROM CollectionCategory c WHERE c.active = :active"),
		@NamedQuery(name = "CollectionCategory.findByServiceInUse", query = "SELECT c FROM CollectionCategory c WHERE c.serviceInUse = :serviceInUse"),
		@NamedQuery(name = "CollectionCategory.findByVendorType", query = "SELECT c FROM CollectionCategory c WHERE c.vendorType = :vendorType"),
		@NamedQuery(name = "CollectionCategory.findByVendorTierGroup", query = "SELECT c FROM CollectionCategory c WHERE c.vendorTierGroup = :vendorTierGroup"),
		@NamedQuery(name = "CollectionCategory.findByOrderBy", query = "SELECT c FROM CollectionCategory c WHERE c.orderBy = :orderBy"),
		@NamedQuery(name = "CollectionCategory.findByModifiedBy", query = "SELECT c FROM CollectionCategory c WHERE c.modifiedBy = :modifiedBy"),
		@NamedQuery(name = "CollectionCategory.findByModifiedOn", query = "SELECT c FROM CollectionCategory c WHERE c.modifiedOn = :modifiedOn") })
public class CollectionCategory implements Serializable {

	// exec up_wa_GetCollectionCategory @CategoryID=0,@ExpandAll=0

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "CollectionCategoryID")
	private Integer collectionCategoryID;

	@Size(max = 50)
	@Column(name = "CollectionCategoryName")
	private String collectionCategoryName;

	@Column(name = "ParentCollectionCategoryID")
	private Integer parentCollectionCategoryID;

	@Column(name = "SpotID")
	private Integer spotID;

	@Column(name = "Lvl")
	private Integer lvl;

	@Column(name = "ListOrder")
	private Integer listOrder;

	@Column(name = "Active")
	private Boolean active;

	@Size(max = 50)
	@Column(name = "ServiceInUse")
	private String serviceInUse;

	@Size(max = 50)
	@Column(name = "VendorType")
	private String vendorType;

	@Size(max = 50)
	@Column(name = "VendorTierGroup")
	private String vendorTierGroup;

	@Size(max = 50)
	@Column(name = "OrderBy")
	private String orderBy;

	@Size(max = 50)
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "ModifiedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Transient
	@Column(name = "CategoryID")
	private Integer categoryID;
	@Transient
	@Column(name = "CategoryName")
	private String categoryName;
	@Transient
	@Column(name = "ParentCategoryID")
	private Integer parentCategoryID;
	@Transient
	@Column(name = "Expended")
	private Boolean expended;
	@Transient
	@Column(name = "NodeCnt")
	private Integer nodeCnt;

	// exec up_wa_GetCategoryList @CategoryID=0,@ExpandAll=1
	@Transient
	@Column(name = "ParentParentCategoryID")
	private Integer parentParentCategoryID;
	@Transient
	@Column(name = "CategoryDescription")
	private String categoryDescription;
	@Transient
	@Column(name = "TitleImage")
	private String titleImage;
	@Transient
	@Column(name = "IsLandingPage")
	private Boolean isLandingPage;
	@Transient
	@Column(name = "IsFeatured")
	private Boolean isFeatured;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CollectionCategory() {
	}

	public CollectionCategory(Integer collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}

	public Integer getCollectionCategoryID() {
		return collectionCategoryID;
	}

	public void setCollectionCategoryID(Integer collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}

	public String getCollectionCategoryName() {
		return collectionCategoryName;
	}

	public void setCollectionCategoryName(String collectionCategoryName) {
		this.collectionCategoryName = collectionCategoryName;
	}

	public Integer getParentCollectionCategoryID() {
		return parentCollectionCategoryID;
	}

	public void setParentCollectionCategoryID(Integer parentCollectionCategoryID) {
		this.parentCollectionCategoryID = parentCollectionCategoryID;
	}

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}

	public Integer getLvl() {
		return lvl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getServiceInUse() {
		return serviceInUse;
	}

	public void setServiceInUse(String serviceInUse) {
		this.serviceInUse = serviceInUse;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getVendorTierGroup() {
		return vendorTierGroup;
	}

	public void setVendorTierGroup(String vendorTierGroup) {
		this.vendorTierGroup = vendorTierGroup;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getParentCategoryID() {
		return parentCategoryID;
	}

	public void setParentCategoryID(Integer parentCategoryID) {
		this.parentCategoryID = parentCategoryID;
	}

	public Boolean getExpended() {
		return expended;
	}

	public void setExpended(Boolean expended) {
		this.expended = expended;
	}

	public Integer getNodeCnt() {
		return nodeCnt;
	}

	public void setNodeCnt(Integer nodeCnt) {
		this.nodeCnt = nodeCnt;
	}

	public Integer getParentParentCategoryID() {
		return parentParentCategoryID;
	}

	public void setParentParentCategoryID(Integer parentParentCategoryID) {
		this.parentParentCategoryID = parentParentCategoryID;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public Boolean getIsLandingPage() {
		return isLandingPage;
	}

	public void setIsLandingPage(Boolean isLandingPage) {
		this.isLandingPage = isLandingPage;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (collectionCategoryID != null ? collectionCategoryID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CollectionCategory)) {
			return false;
		}
		CollectionCategory other = (CollectionCategory) object;
		if ((this.collectionCategoryID == null && other.collectionCategoryID != null)
				|| (this.collectionCategoryID != null
						&& !this.collectionCategoryID.equals(other.collectionCategoryID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.mavenproject3.CollectionCategory[ collectionCategoryID=" + collectionCategoryID + " ]";
	}

}
