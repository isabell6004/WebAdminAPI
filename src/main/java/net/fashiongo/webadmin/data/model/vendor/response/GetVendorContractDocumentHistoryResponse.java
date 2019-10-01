package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorContractDocumentHistory;

import java.util.List;

@Getter
@Builder
public class GetVendorContractDocumentHistoryResponse {

	@JsonProperty("Table")
	private List<VendorContractDocumentHistory> vendorContractDocumentHistoryList;
}
