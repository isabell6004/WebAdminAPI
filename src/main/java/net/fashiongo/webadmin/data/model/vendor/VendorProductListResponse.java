package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import net.fashiongo.webadmin.data.entity.primary.vendor.VendorProductRow;

import java.util.List;

@Value
@Builder
public class VendorProductListResponse {
	@JsonProperty("Table")
	private List<VendorProductRow> products;
}
