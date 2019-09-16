package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BannerRequestResponse {

	@JsonProperty("Table")
	private List<VendorImageRequestResponse> bannerImageList;

	@JsonProperty("Table1")
	private List<BannerRequestCount> total;
}
