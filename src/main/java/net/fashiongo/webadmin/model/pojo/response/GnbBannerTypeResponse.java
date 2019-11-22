package net.fashiongo.webadmin.model.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.GnbMenuBannerTypeEntity;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class GnbBannerTypeResponse {
	@JsonProperty("menuBannerTypeId")
	private Integer menuBannerTypeId;
	@JsonProperty("menuBannerTypeName")
	private String menuBannerTypeName;
	@JsonProperty("modifiedOn")
	private LocalDateTime modifiedOn;
	@JsonProperty("banners")
	List<GnbBannerResponse> banners;

	public static GnbBannerTypeResponse convertFrom(GnbMenuBannerTypeEntity entity, String imageStorageUrl) {
		List<GnbBannerResponse> bannerResponses;
		if (CollectionUtils.isEmpty(entity.getBanners())) {
			bannerResponses = Collections.emptyList();
		} else {
			bannerResponses = entity.getBanners().stream()
					.map(e -> GnbBannerResponse.convertFrom(e, imageStorageUrl))
					.collect(Collectors.toList());
		}
		return GnbBannerTypeResponse.builder()
				.menuBannerTypeId(entity.getMenuBannerTypeId())
				.menuBannerTypeName(entity.getMenuBannerTypeName())
				.modifiedOn(entity.getModifiedOn())
				.banners(bannerResponses)
				.build();
	}
}
