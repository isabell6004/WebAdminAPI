package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class VendorContractDocumentHistory {

	@JsonProperty("VendorContractDocumentID")
	private Integer vendorContractDocumentID;

	@JsonProperty("VendorContractID")
	private Integer vendorContractID;

	@JsonProperty("DocumentTypeID")
	private Integer documentTypeID;

	@JsonProperty("FileName")
	private String fileName;

	@JsonProperty("FileName2")
	private String fileName2;

	@JsonProperty("FileName3")
	private String fileName3;

	@JsonProperty("Note")
	private String note;

	@JsonProperty("ReceivedBy")
	private String receivedBy;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("CheckBox")
	private Boolean checkBox;
}
