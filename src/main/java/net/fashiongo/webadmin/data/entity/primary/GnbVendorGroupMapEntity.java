package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.data.entity.primary.vendor.WholesalerCompanyEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "gnb_vendor_group_map")
public class GnbVendorGroupMapEntity {
	@EmbeddedId
	private GnbVendorGroupMapId mapId;

	@Column(name = "sort_no")
	private int sortNo;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;

	@Column(name = "modified_by")
	private String modifiedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id", referencedColumnName = "WholeSalerID", insertable = false, updatable = false)
	private WholesalerCompanyEntity vendor;
}
