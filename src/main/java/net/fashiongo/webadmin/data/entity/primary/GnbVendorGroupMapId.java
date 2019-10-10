package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class GnbVendorGroupMapId implements Serializable {
	@Column(name = "vendor_group_id")
	private Integer vendorGroupId;

	@Column(name = "vendor_id")
	private Integer vendorId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GnbVendorGroupMapId that = (GnbVendorGroupMapId) o;
		return Objects.equals(vendorGroupId, that.vendorGroupId) &&
				Objects.equals(vendorId, that.vendorId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vendorGroupId, vendorId);
	}
}
