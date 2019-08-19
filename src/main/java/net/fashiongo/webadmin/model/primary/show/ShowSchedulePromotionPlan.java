package net.fashiongo.webadmin.model.primary.show;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.common.conversion.LocalDateTimeConverter;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "ShowSchedule_PromotionPlan")
@XmlRootElement
/*@NamedQueries({
		@NamedQuery(name = "ShowSchedulePromotionPlan.findDetail", query = "SELECT s FROM ShowSchedulePromotionPlan s"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByPlanID", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.planID = :planID"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByPlanName", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.planName = :planName"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByShowScheduleID", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.showScheduleID = :showScheduleID"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByIsOnline", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.isOnline = :isOnline"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByIsOffline", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.isOffline = :isOffline"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByCommissionEffectiveFrom", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.commissionEffectiveFrom = :commissionEffectiveFrom"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByCommissionEffectiveTo", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.commissionEffectiveTo = :commissionEffectiveTo"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByCreatedOn", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.createdOn = :createdOn"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByCreatedBy", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.createdBy = :createdBy"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByModifiedOn", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.modifiedOn = :modifiedOn"),
		@NamedQuery(name = "ShowSchedulePromotionPlan.findByModifiedBy", query = "SELECT s FROM ShowSchedulePromotionPlan s WHERE s.modifiedBy = :modifiedBy") })
*/
public class ShowSchedulePromotionPlan implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "PlanID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("planId")
	private Integer planID;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "PlanName")
	@JsonProperty("")
	private String planName;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ShowScheduleID")
	@JsonProperty("")
	private int showScheduleID;

	@Basic(optional = false)
	@NotNull
	@Column(name = "IsOnline")
	@JsonProperty("")
	private boolean isOnline;

	@Basic(optional = false)
	@NotNull
	@Column(name = "IsOffline")
	@JsonProperty("")
	private boolean isOffline;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CommissionEffectiveFrom")
//	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime commissionEffectiveFrom;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CommissionEffectiveTo")
//	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime commissionEffectiveTo;

	@Column(name = "CreatedOn")
//	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOn;

	@Size(max = 50)
	@Column(name = "CreatedBy")
	@JsonProperty("")
	private String createdBy;

	@Column(name = "ModifiedOn")
//	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOn;

	@Size(max = 50)
	@Column(name = "ModifiedBy")
	@JsonProperty("")
	private String modifiedBy;

	@Transient
	@JsonProperty("isDeletable")
	private Boolean isDeletable;

	public ShowSchedulePromotionPlan() {
	}

	public ShowSchedulePromotionPlan(Integer planID) {
		this.planID = planID;
	}

	public ShowSchedulePromotionPlan(Integer planID, String planName, int showScheduleID, boolean isOnline,
			boolean isOffline, LocalDateTime commissionEffectiveFrom, LocalDateTime commissionEffectiveTo) {
		this.planID = planID;
		this.planName = planName;
		this.showScheduleID = showScheduleID;
		this.isOnline = isOnline;
		this.isOffline = isOffline;
		this.commissionEffectiveFrom = commissionEffectiveFrom;
		this.commissionEffectiveTo = commissionEffectiveTo;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getShowScheduleID() {
		return showScheduleID;
	}

	public void setShowScheduleID(int showScheduleID) {
		this.showScheduleID = showScheduleID;
	}

	public boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public boolean getIsOffline() {
		return isOffline;
	}

	public void setIsOffline(boolean isOffline) {
		this.isOffline = isOffline;
	}

	public LocalDateTime getCommissionEffectiveFrom() {
		return commissionEffectiveFrom;
	}

	public void setCommissionEffectiveFrom(LocalDateTime commissionEffectiveFrom) {
		this.commissionEffectiveFrom = commissionEffectiveFrom;
	}

	public LocalDateTime getCommissionEffectiveTo() {
		return commissionEffectiveTo;
	}

	public void setCommissionEffectiveTo(LocalDateTime commissionEffectiveTo) {
		this.commissionEffectiveTo = commissionEffectiveTo;
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

	public Boolean getIsDeletable() {
		return isDeletable == null ? true : isDeletable;
	}

	public void setIsDeletable(Boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public void setOffline(boolean isOffline) {
		this.isOffline = isOffline;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (planID != null ? planID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ShowSchedulePromotionPlan)) {
			return false;
		}
		ShowSchedulePromotionPlan other = (ShowSchedulePromotionPlan) object;
		if ((this.planID == null && other.planID != null)
				|| (this.planID != null && !this.planID.equals(other.planID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.mavenproject3.ShowSchedulePromotionPlan[ planID=" + planID + " ]";
	}

}
