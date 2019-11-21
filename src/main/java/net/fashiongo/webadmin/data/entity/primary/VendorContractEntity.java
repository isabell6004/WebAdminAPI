package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Entity
@Setter
@Table(name = "Vendor_Contract")
public class VendorContractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "VendorContractID")
	private Integer vendorContractID;

	@Column(name = "WholeSalerID")
	private int wholeSalerID;

	@Column(name = "ContractTypeID")
	private int contractTypeID;

	@Column(name = "SetupFee")
	private BigDecimal setupFee;

	@Column(name = "LastMonthServiceFee")
	private BigDecimal lastMonthServiceFee;

	@Column(name = "PhotoPlanID")
	private int photoPlanID;

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
	private boolean perorder;

	@Column(name = "Memo")
	private String memo;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "MonthlyFee")
	private BigDecimal monthlyFee;

	@Column(name = "FromContractDate")
	private Timestamp fromContractDate;

	@Column(name = "ToContractDate")
	private Timestamp toContractDate;

	@Column(name = "IsSetupFeeWaived")
	private Boolean isSetupFeeWaived;

	@Column(name = "IsLastMonthServiceFeeWaived")
	private Boolean isLastMonthServiceFeeWaived;

	@Column(name = "IsMonthlyFee")
	private Boolean isMonthlyFee=true;

	@Column(name = "vendor_contract_plan_id")
	private Integer vendorContractPlanId;

	@Column(name = "commission_base_date_code")
	private Integer commissionBaseDateCode;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "VendorContractID", referencedColumnName = "VendorContractID", insertable = false, updatable =  false)
	private List<VendorContractDocumentEntity> vendorContractDocumentList;

}
