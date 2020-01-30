package net.fashiongo.webadmin.model.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.GnbMenuCollectionEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class GnbCollectionInfoResponse {
	private int gnbMenuCollectionId;
	private String name;
	
	@JsonProperty("targetUrl")
	private String targeturl;
	
	@JsonProperty("sortNo")
	private Integer sortno;
	
	@JsonProperty("isActive")
	private boolean active;	
	
    private String createdBy;
	
	private LocalDateTime createdOn;
	
	private String modifiedBy;
	
	private LocalDateTime modifiedOn;
	
	
	public static GnbCollectionInfoResponse of(GnbMenuCollectionEntity gnbMenuCollectionEntity) {
		return GnbCollectionInfoResponse.builder()
				.gnbMenuCollectionId(gnbMenuCollectionEntity.getMenuCollectionId())
			    .name(gnbMenuCollectionEntity.getMenuCollectionName())
				.targeturl(gnbMenuCollectionEntity.getTargetUrl())
				.sortno(gnbMenuCollectionEntity.getSortNo())	
				.active(gnbMenuCollectionEntity.isActive())
				.createdBy(gnbMenuCollectionEntity.getCreatedBy())
				.createdOn(gnbMenuCollectionEntity.getCreatedOn())
				.modifiedBy(gnbMenuCollectionEntity.getModifiedBy())
				.modifiedOn(gnbMenuCollectionEntity.getModifiedOn())	
				.build();
				
	}

}
