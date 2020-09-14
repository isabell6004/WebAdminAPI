package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "gnb_vendor_group_map")
public class GnbVendorGroupMapEntity implements Persistable<GnbVendorGroupMapId> {
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
	@JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id", insertable = false, updatable = false)
	private VendorEntity vendor;

	@Transient
	private boolean isNew = true;

	@PrePersist
	@PostLoad
	private void markNotNew() {
		this.isNew = false;
	}

	@Override
	public GnbVendorGroupMapId getId() {
		return mapId;
	}

	@Override
	public boolean isNew() {
		return isNew;
	}
}
