package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.utility.Utility;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "gnb_vendor_group")
@Getter
@Setter
public class GnbVendorGroupEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_group_id")
	@Setter(AccessLevel.NONE)
	private Integer vendorGroupId;

	@Column(name = "vendor_group_title")
	private String vendorGroupTitle;

	@Column(name = "target_url")
	private String targetUrl;

	@Column(name = "is_alphabetical_order")
	private boolean isAlphabeticalOrder;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "vendor_group_type")
	private Integer vendorGroupType;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_group_id", referencedColumnName = "vendor_group_id", insertable = false, updatable = false)
	private List<GnbVendorGroupMapEntity> vendorGroupMaps;

	@PreUpdate
	protected void preUpdate() {
		modifiedOn = LocalDateTime.now();
		modifiedBy = Utility.getUsername();
	}
}
