package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "gnb_menu_banner")
public class GnbMenuBannerEntity {
	@Id
	@GeneratedValue
	@Column(name = "menu_banner_id")
	@Setter(AccessLevel.NONE)
	private Integer menuBannerId;

	@Column(name = "menu_banner_type_id")
	private int menuBannerTypeId;

	@Column(name = "image_file_name")
	private String imageFileName;

	@Column(name = "target_url")
	private String targetUrl;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;

	@Column(name = "modified_by")
	private String modifiedBy;
}
