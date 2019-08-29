package net.fashiongo.webadmin.data.entity.primary.show;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Map_ShowSchedule_PromotionPlan_Vendor")
@Getter
@Setter
public class MapShowSchedulePromotionPlanVendorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MapID")
	private Integer mapId;

	@Column(name = "PlanID")
	private int planId;

	@Column(name = "WholeSalerID")
	private int wholesalerId;

	@Column(name = "CommissionRate")
	private BigDecimal commissionRate;

	@Column(name = "RackCount")
	private int rackCount;

	@Column(name = "Fee")
	private BigDecimal fee;

	@Column(name = "ItemCountMax")
	private int itemCountMax;

	@Column(name = "Isdeleted")
	private Boolean isDeleted;

	@Column(name = "CancelDate")
	private LocalDateTime cancelDate;

	@Column(name = "CanceledBy")
	private String canceledBy;

	@Column(name = "Active")
	private Boolean isActive;

	@Column(name = "discount_amount")
	private BigDecimal discountAmount;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;
}
