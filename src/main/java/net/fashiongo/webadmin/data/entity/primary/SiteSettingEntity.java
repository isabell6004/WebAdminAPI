package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Site_Setting")
@Getter
@Setter
public class SiteSettingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@Setter(AccessLevel.NONE)
	private int id;

	@Column(name = "gnb_vendor_group_id")
	private Integer gnbVendorGroupId;
}
