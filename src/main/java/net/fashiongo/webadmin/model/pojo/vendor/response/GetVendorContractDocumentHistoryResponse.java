package net.fashiongo.webadmin.model.pojo.vendor.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.vendor.ProductSummary;
import net.fashiongo.webadmin.model.pojo.vendor.VendorContractDocumentHistory;

public class GetVendorContractDocumentHistoryResponse {
	@JsonProperty("Table")
	private List<VendorContractDocumentHistory> vendorContractDocumentHistoryList;

	public List<VendorContractDocumentHistory> getVendorContractDocumentHistoryList() {
		return vendorContractDocumentHistoryList;
	}

	public void setVendorContractDocumentHistoryList(
			List<VendorContractDocumentHistory> vendorContractDocumentHistoryList) {
		this.vendorContractDocumentHistoryList = vendorContractDocumentHistoryList;
	}
	
	
}
