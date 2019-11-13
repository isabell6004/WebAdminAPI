package net.fashiongo.webadmin.model.pojo.response;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupMapEntity;

@Getter
@Builder
public class GnbVendorGroupMapResponse {
	private int wholeSalerId;

	private String companyName;

	public static GnbVendorGroupMapResponse of(GnbVendorGroupMapEntity gnbVendorGroupMapEntity) {
		return GnbVendorGroupMapResponse.builder()
				.wholeSalerId(gnbVendorGroupMapEntity.getId().getVendorId())
				.companyName(gnbVendorGroupMapEntity.getVendor().getCompanyName())
				.build();
	}
}
