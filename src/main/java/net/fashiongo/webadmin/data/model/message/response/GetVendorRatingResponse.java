package net.fashiongo.webadmin.data.model.message.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.message.VendorRating;

import java.util.List;

@Getter
@Builder
public class GetVendorRatingResponse {

	@JsonProperty("Table")
	private List<Total> recCnt;

	@JsonProperty("Table1")
	private List<VendorRating> vendorRatingList;
}
