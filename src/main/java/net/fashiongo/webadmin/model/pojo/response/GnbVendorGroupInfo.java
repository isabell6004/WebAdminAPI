package net.fashiongo.webadmin.model.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class GnbVendorGroupInfo {
	private int gnbVendorGroupId;
	
	private String title;
	
	private int numberOfVendors;
	
	private String createdBy;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime modifiedOn;

	@JsonProperty("isActive")
	private boolean active;
	
	public static GnbVendorGroupInfo of(GnbVendorGroupEntity gnbVendorGroupEntity, boolean isActive) {
		return GnbVendorGroupInfo.builder()
				.gnbVendorGroupId(gnbVendorGroupEntity.getVendorGroupId())
				.title(gnbVendorGroupEntity.getVendorGroupTitle())
				.numberOfVendors(gnbVendorGroupEntity.getVendorGroupMaps().size())
				.createdBy(gnbVendorGroupEntity.getCreatedBy())
				.createdOn(gnbVendorGroupEntity.getCreatedOn())
				.modifiedOn(gnbVendorGroupEntity.getModifiedOn())
				.active(isActive)
				.build();
	}
}
