package net.fashiongo.webadmin.model.primary.show;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "Map_ShowSchedule_PromotionPlan_Vendor")
@XmlRootElement
/*@NamedQueries({
		@NamedQuery(name = "MapShowSchedulePromotionPlanVendor.findDetail", query = "SELECT m FROM MapShowSchedulePromotionPlanVendor m"),
		@NamedQuery(name = "MapShowSchedulePromotionPlanVendor.findByMapID", query = "SELECT m FROM MapShowSchedulePromotionPlanVendor m WHERE m.mapID = :mapID"),
		@NamedQuery(name = "MapShowSchedulePromotionPlanVendor.findByPlanID", query = "SELECT m FROM MapShowSchedulePromotionPlanVendor m WHERE m.planID = :planID"),
		@NamedQuery(name = "MapShowSchedulePromotionPlanVendor.findByWholeSalerID", query = "SELECT m FROM MapShowSchedulePromotionPlanVendor m WHERE m.wholeSalerID = :wholeSalerID"),
		@NamedQuery(name = "MapShowSchedulePromotionPlanVendor.findByCommissionRate", query = "SELECT m FROM MapShowSchedulePromotionPlanVendor m WHERE m.commissionRate = :commissionRate"),
		@NamedQuery(name = "MapShowSchedulePromotionPlanVendor.findByRackCount", query = "SELECT m FROM MapShowSchedulePromotionPlanVendor m WHERE m.rackCount = :rackCount"),
		@NamedQuery(name = "MapShowSchedulePromotionPlanVendor.findByFee", query = "SELECT m FROM MapShowSchedulePromotionPlanVendor m WHERE m.fee = :fee"),
		@NamedQuery(name = "MapShowSchedulePromotionPlanVendor.findByItemCountMax", query = "SELECT m FROM MapShowSchedulePromotionPlanVendor m WHERE m.itemCountMax = :itemCountMax") })
*/
public class MapShowSchedulePromotionPlanVendor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "MapID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("MapID")
	private Integer mapID;

	@Basic(optional = false)
	@NotNull
	@Column(name = "PlanID")
	@JsonProperty("PlanID")
	private int planID;

	@Basic(optional = false)
	@NotNull
	@Column(name = "WholeSalerID")
	@JsonProperty("WholeSalerID")
	private int wholeSalerID;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CommissionRate")
	@JsonProperty("CommissionRate")
	private double commissionRate;

	@Basic(optional = false)
	@NotNull
	@Column(name = "RackCount")
	@JsonProperty("RackCount")
	private int rackCount;

	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Column(name = "Fee")
	@JsonProperty("Fee")
	private BigDecimal fee;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ItemCountMax")
	@JsonProperty("ItemCountMax")
	private int itemCountMax;

	@Transient
	@Column(name = "row")
	@JsonProperty("")
	private long row;

	@Transient
	@Column(name = "ShowScheduleID")
	@JsonProperty("ShowScheduleID")
	private int showScheduleID;

	@Transient
	@Column(name = "CompanyName")
	@JsonProperty("CompanyName")
	private String companyName;

	@Transient
	@Column(name = "PlanName")
	@JsonProperty("PlanName")
	private String planName;

	@Transient
	@Column(name = "DeleteFlag")
	@JsonProperty("DeleteFlag")
	private int deleteFlag;

	@Column(name = "IsDeleted")
	private Boolean isDeleted;

	@Column(name = "CanceledBy")
	@JsonProperty("CanceledBy")
	private String canceledBy;

	@JsonProperty("CancelDate")
	@Column(name = "CancelDate")
	private LocalDateTime cancelDate;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;

	public MapShowSchedulePromotionPlanVendor() {
	}

	public MapShowSchedulePromotionPlanVendor(Integer mapID) {
		this.mapID = mapID;
	}

	public MapShowSchedulePromotionPlanVendor(Integer mapID, int planID, int wholeSalerID, double commissionRate,
			int rackCount, BigDecimal fee, int itemCountMax) {
		this.mapID = mapID;
		this.planID = planID;
		this.wholeSalerID = wholeSalerID;
		this.commissionRate = commissionRate;
		this.rackCount = rackCount;
		this.fee = fee;
		this.itemCountMax = itemCountMax;
	}

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public int getPlanID() {
		return planID;
	}

	public void setPlanID(int planID) {
		this.planID = planID;
	}

	public int getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(int wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public int getRackCount() {
		return rackCount;
	}

	public void setRackCount(int rackCount) {
		this.rackCount = rackCount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public int getItemCountMax() {
		return itemCountMax;
	}

	public void setItemCountMax(int itemCountMax) {
		this.itemCountMax = itemCountMax;
	}

	public long getRow() {
		return row;
	}

	public void setRow(long row) {
		this.row = row;
	}

	public int getShowScheduleID() {
		return showScheduleID;
	}

	public void setShowScheduleID(int showScheduleID) {
		this.showScheduleID = showScheduleID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCanceledBy() {
		return canceledBy;
	}

	public void setCanceledBy(String canceledBy) {
		this.canceledBy = canceledBy;
	}

	public LocalDateTime getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(LocalDateTime cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (mapID != null ? mapID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MapShowSchedulePromotionPlanVendor)) {
			return false;
		}
		MapShowSchedulePromotionPlanVendor other = (MapShowSchedulePromotionPlanVendor) object;
		if ((this.mapID == null && other.mapID != null) || (this.mapID != null && !this.mapID.equals(other.mapID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.mavenproject3.MapShowSchedulePromotionPlanVendor[ mapID=" + mapID + " ]";
	}

}
