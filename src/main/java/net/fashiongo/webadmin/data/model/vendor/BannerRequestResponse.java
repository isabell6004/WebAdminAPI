package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BannerRequestResponse {
	@JsonProperty("contents")
	private List<VendorBannerResponse> bannerImageList;

	@JsonProperty("totalCount")
	private Long total;

	public BannerRequestResponse() {
	}

	@Builder
	public BannerRequestResponse(List<VendorBannerResponse> bannerImageList, Long total) {
		this.bannerImageList = bannerImageList;
		this.total = total;
	}
}
