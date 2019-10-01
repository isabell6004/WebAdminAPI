package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class VendorFormListResponse {
	@JsonProperty("Table")
	private List<VendorFormResponse> fashionGoFormList;
}
