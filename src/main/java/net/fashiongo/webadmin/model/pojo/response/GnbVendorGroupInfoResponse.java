package net.fashiongo.webadmin.model.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
public class GnbVendorGroupInfoResponse {
	private int gnbVendorGroupId;
	
	private String title;
	
	private int numberOfVendors;
	
	private String createdBy;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime modifiedOn;

	@JsonProperty("isActive")
	private boolean active;
	
	public static GnbVendorGroupInfoResponse of(GnbVendorGroupEntity gnbVendorGroupEntity, boolean isActive) {
		return GnbVendorGroupInfoResponse.builder()
				.gnbVendorGroupId(gnbVendorGroupEntity.getVendorGroupId())
				.title(gnbVendorGroupEntity.getVendorGroupTitle())
				.numberOfVendors(Optional.ofNullable(gnbVendorGroupEntity.getVendorGroupMaps())
						.map(List::size)
						.orElse(0))
				.createdBy(gnbVendorGroupEntity.getCreatedBy())
				.createdOn(gnbVendorGroupEntity.getCreatedOn())
				.modifiedOn(gnbVendorGroupEntity.getModifiedOn())
				.active(isActive)
				.build();
	}
}
