package net.fashiongo.webadmin.model.primary;

import java.math.BigDecimal;
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
 * @author Reo
 *
 */
@Entity
@Table(name = "Vendor_Contract")
public class VendorContract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("VendorContractID")
	@Column(name = "VendorContractID")
	private Integer vendorContractID;
	
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	
	@Column(name = "ContractTypeID")
	private Integer contractTypeID;
	
	@Column(name = "SetupFee")
	private BigDecimal setupFee;
	
	@Column(name = "LastMonthServiceFee")
	private BigDecimal lastMonthServiceFee;
	
	@Column(name = "PhotoPlanID")
	private Integer photoPlanID;
	
	@Column(name = "UseModel")
	private Boolean useModel;
	
	@Column(name = "UseModelStyle")
	private String useModelStyle;
	
	@Column(name = "MonthlyItems")
	private Integer monthlyItems;
	
	@Column(name = "CommissionRate")
	private BigDecimal commissionRate;
	
	@Column(name = "RepID")
	private Integer repID;
	
	@Column(name = "Perorder")
	private Boolean perorder;
	
	@Column(name = "Memo")
	private String memo;
	
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "MonthlyFee")
	private BigDecimal monthlyFee;
	
	@Column(name = "FromContractDate")
	private LocalDateTime fromContractDate;
	
	@Column(name = "ToContractDate")
	private LocalDateTime toContractDate;
	
	@Column(name = "IsSetupFeeWaived")
	private Boolean isSetupFeeWaived;
	
	@Column(name = "IsLastMonthServiceFeeWaived")
	private Boolean isLastMonthServiceFeeWaived;

	@Column(name = "vendor_contract_plan_id")
	private Integer vendorContractPlanId;

	@Column(name = "commission_base_date_code")
	private Integer commissionBaseDateCode;

	public Integer getVendorContractID() {
		return vendorContractID;
	}

	public void setVendorContractID(Integer vendorContractID) {
		this.vendorContractID = vendorContractID;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public Integer getContractTypeID() {
		return contractTypeID;
	}

	public void setContractTypeID(Integer contractTypeID) {
		this.contractTypeID = contractTypeID;
	}

	public BigDecimal getSetupFee() {
		return setupFee;
	}

	public void setSetupFee(BigDecimal setupFee) {
		this.setupFee = setupFee;
	}

	public BigDecimal getLastMonthServiceFee() {
		return lastMonthServiceFee;
	}

	public void setLastMonthServiceFee(BigDecimal lastMonthServiceFee) {
		this.lastMonthServiceFee = lastMonthServiceFee;
	}

	public Integer getPhotoPlanID() {
		return photoPlanID;
	}

	public void setPhotoPlanID(Integer photoPlanID) {
		this.photoPlanID = photoPlanID;
	}

	public Boolean getUseModel() {
		return useModel;
	}

	public void setUseModel(Boolean useModel) {
		this.useModel = useModel;
	}

	public String getUseModelStyle() {
		return useModelStyle;
	}

	public void setUseModelStyle(String useModelStyle) {
		this.useModelStyle = useModelStyle;
	}

	public Integer getMonthlyItems() {
		return monthlyItems;
	}

	public void setMonthlyItems(Integer monthlyItems) {
		this.monthlyItems = monthlyItems;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Integer getRepID() {
		return repID;
	}

	public void setRepID(Integer repID) {
		this.repID = repID;
	}

	public Boolean getPerorder() {
		return perorder;
	}

	public void setPerorder(Boolean perorder) {
		this.perorder = perorder;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public BigDecimal getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(BigDecimal monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public LocalDateTime getFromContractDate() {
		return fromContractDate;
	}

	public void setFromContractDate(LocalDateTime fromContractDate) {
		this.fromContractDate = fromContractDate;
	}

	public LocalDateTime getToContractDate() {
		return toContractDate;
	}

	public void setToContractDate(LocalDateTime toContractDate) {
		this.toContractDate = toContractDate;
	}

	public Boolean getIsSetupFeeWaived() {
		return isSetupFeeWaived;
	}

	public void setIsSetupFeeWaived(Boolean isSetupFeeWaived) {
		this.isSetupFeeWaived = isSetupFeeWaived;
	}

	public Boolean getIsLastMonthServiceFeeWaived() {
		return isLastMonthServiceFeeWaived;
	}

	public void setIsLastMonthServiceFeeWaived(Boolean isLastMonthServiceFeeWaived) {
		this.isLastMonthServiceFeeWaived = isLastMonthServiceFeeWaived;
	}

	public Integer getVendorContractPlanId() { return vendorContractPlanId; }

	public void setVendorContractPlanId(Integer vendorContractPlanId) { this.vendorContractPlanId = vendorContractPlanId; }

	public Integer getCommissionBaseDateCode() { return commissionBaseDateCode; }

	public void setCommissionBaseDateCode(Integer commissionBaseDateCode) { this.commissionBaseDateCode = commissionBaseDateCode; }
	
}
