package net.fashiongo.webadmin.data.entity.primary.show;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tblWholeSaler")
@Getter
@Setter
public class WholesalerWithPromotionEntity {
	@Id
	@Column(name = "WholeSalerID")
	private Integer wholesalerId;

	@Column(name = "CompanyName")
	private String companyName;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "WholeSalerID", referencedColumnName = "WholeSalerID")
	private Set<MapShowSchedulePromotionPlanVendorEntity> mapShowSchedulePromotionPlanVendors;
}
