package net.fashiongo.webadmin.model.primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

@Entity
@Table(name = "vendor_contract_plan")
@Data
public class ContractPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_contract_plan_id")
	private Integer vendorContractPlanId;
	
	@Column(name = "contract_plan_name")
	private String contractPlanName;
	
	@Column(name = "contract_type_code")
	private Integer contractTypeCode;
	
	@Column(name = "setup_fee")
	private BigDecimal setupFee;
	
	@Column(name = "last_month_service_fee")
	private BigDecimal lastMonthServiceFee;
	
	@Column(name = "monthly_fee")
	private BigDecimal monthlyFee;
	
	@Column(name = "monthly_item_cap")
	private Integer monthlyItemCap;
	
	@Column(name = "commission_rate")
	private BigDecimal commissionRate;
	
	@Column(name = "commission_base_date_code")
	private Integer commissionBaseDateCode;
	
	@Column(name = "memo")
	private String memo;
	
	@Column(name = "is_editable")
	private Boolean isEditable;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "created_on")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOn;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_on")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOn;
	
	@Column(name = "modified_by")
	private String modifiedBy;
}
