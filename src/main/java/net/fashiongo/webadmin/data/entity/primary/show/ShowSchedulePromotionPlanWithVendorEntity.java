package net.fashiongo.webadmin.data.entity.primary.show;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ShowSchedule_PromotionPlan")
@Getter
@Setter
public class ShowSchedulePromotionPlanWithVendorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PlanID")
	private Integer planId;

	@Column(name = "PlanName")
	private String planName;

	@Column(name = "ShowScheduleID")
	private int showScheduleId;

	@Column(name = "IsOnline")
	private boolean isOnline;

	@Column(name = "IsOffline")
	private boolean isOffline;

	@Column(name = "CommissionEffectiveFrom")
	private LocalDateTime commissionEffectiveFrom;

	@Column(name = "CommissionEffectiveTo")
	private LocalDateTime commissionEffectiveTo;

	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@OneToMany
	@JoinColumn(name = "PlanID", referencedColumnName = "PlanID")
	private Set<MapShowSchedulePromotionPlanVendorEntity> mapShowSchedulePromotionPlanVendors;
}
