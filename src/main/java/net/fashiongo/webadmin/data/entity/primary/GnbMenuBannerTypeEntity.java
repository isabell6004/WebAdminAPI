package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "gnb_menu_banner_type")
public class GnbMenuBannerTypeEntity {
	@Id
	@GeneratedValue
	@Column(name = "menu_banner_type_id")
	@Setter(AccessLevel.NONE)
	private Integer menuBannerTypeId;

	@Column(name = "menu_banner_type_name")
	private String menuBannerTypeName;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;

	@Column(name = "modified_by")
	private String modifiedBy;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_banner_type_id", referencedColumnName = "menu_banner_type_id")
	List<GnbMenuBannerEntity> banners;
}
