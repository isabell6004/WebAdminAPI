package net.fashiongo.webadmin.model.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Builder
public class GnbVendorGroupDetailResponse {
	private int gnbVendorGroupId;

	private String title;

	private String targetUrl;

	private int vendorGroupType;

	@JsonProperty("isAlphabeticalOrder")
	private boolean isAlphabeticalOrder;

	private List<GnbVendorGroupMapResponse> vendorGroupMapList;

	public static GnbVendorGroupDetailResponse of(GnbVendorGroupEntity gnbVendorGroupEntity) {
		return GnbVendorGroupDetailResponse.builder()
				.gnbVendorGroupId(gnbVendorGroupEntity.getVendorGroupId())
				.title(gnbVendorGroupEntity.getVendorGroupTitle())
				.targetUrl(gnbVendorGroupEntity.getTargetUrl())
				.vendorGroupType(gnbVendorGroupEntity.getVendorGroupType())
				.isAlphabeticalOrder(gnbVendorGroupEntity.isAlphabeticalOrder())
				.vendorGroupMapList(Optional.ofNullable(gnbVendorGroupEntity.getVendorGroupMaps())
						.map(entity -> entity.stream()
								.map(GnbVendorGroupMapResponse::of)
								.collect(Collectors.toList()))
						.orElse(null))
				.build();
	}
}
