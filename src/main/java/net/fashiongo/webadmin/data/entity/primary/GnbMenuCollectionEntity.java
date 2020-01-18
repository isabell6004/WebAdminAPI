package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.utility.Utility;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "gnb_menu_collection")
@Getter
@Setter
public class GnbMenuCollectionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_collection_id")
	@Setter(AccessLevel.NONE)
	private Integer menuCollectionId;

	@Column(name = "menu_collection_name")
	private String menuCollectionName;

	@Column(name = "target_url")
	private String targetUrl;

	@Column(name = "sort_no")
	private Integer sortNo;
	
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

	@PreUpdate
	protected void preUpdate() {
		modifiedOn = LocalDateTime.now();
		modifiedBy = Utility.getUsername();
	}
}
