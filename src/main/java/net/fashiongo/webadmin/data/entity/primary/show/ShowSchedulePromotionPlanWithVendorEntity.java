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
	private Integer PlanID;

	@Column(name = "PlanName")
	private String PlanName;

	@Column(name = "ShowScheduleID")
	private int ShowScheduleID;

	@Column(name = "IsOnline")
	private boolean IsOnline;

	@Column(name = "IsOffline")
	private boolean IsOffline;

	@Column(name = "CommissionEffectiveFrom")
	private LocalDateTime CommissionEffectiveFrom;

	@Column(name = "CommissionEffectiveTo")
	private LocalDateTime CommissionEffectiveTo;

	@Column(name = "CreatedOn")
	private LocalDateTime CreatedOn;

	@Column(name = "CreatedBy")
	private String CreatedBy;

	@Column(name = "ModifiedOn")
	private LocalDateTime ModifiedOn;

	@Column(name = "ModifiedBy")
	private String ModifiedBy;

	@OneToMany
	@JoinColumn(name = "PlanID", referencedColumnName = "PlanID")
	private Set<MapShowSchedulePromotionPlanVendorEntity> mapShowSchedulePromotionPlanVendors;
}
