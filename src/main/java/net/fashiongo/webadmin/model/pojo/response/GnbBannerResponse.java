package net.fashiongo.webadmin.model.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.GnbMenuBannerEntity;

@Getter
@Builder
public class GnbBannerResponse {
	@JsonProperty("menuBannerId")
	private Integer menuBannerId;
	@JsonProperty("menuBannerTypeId")
	private int menuBannerTypeId;
	@JsonProperty("imageFileName")
	private String imageFileName;
	@JsonProperty("targetUrl")
	private String targetUrl;
	@JsonProperty("isActive")
	private boolean isActive;

	@JsonProperty("imageUrl")
	private String imageUrl;

	public static GnbBannerResponse convertFrom(GnbMenuBannerEntity entity, String imageStorageUrl) {
		return GnbBannerResponse.builder()
				.menuBannerId(entity.getMenuBannerId())
				.menuBannerTypeId(entity.getMenuBannerTypeId())
				.imageFileName(entity.getImageFileName())
				.targetUrl(entity.getTargetUrl())
				.isActive(entity.isActive())
				.imageUrl(imageStorageUrl + "/" + entity.getImageFileName())
				.build();
	}
}
