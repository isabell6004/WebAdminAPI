package net.fashiongo.webadmin.data.model.vendor;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VendorContractDocumentHistory {

	private Long id;
	private Long vendorContractHistoryId;
	private Integer typeCode;
	private String fileName1;
	private String fileName2;
	private String fileName3;
	private String note;
	private LocalDateTime createdOn;
	private Integer receivedId;
	private String receivedBy;

	public VendorContractDocumentHistory() {
	}

	@Builder
	public VendorContractDocumentHistory(Long id, Long vendorContractHistoryId, Integer typeCode, String fileName1, String fileName2, String fileName3, String note, LocalDateTime createdOn, Integer receivedId, String receivedBy) {
		this.id = id;
		this.vendorContractHistoryId = vendorContractHistoryId;
		this.typeCode = typeCode;
		this.fileName1 = fileName1;
		this.fileName2 = fileName2;
		this.fileName3 = fileName3;
		this.note = note;
		this.createdOn = createdOn;
		this.receivedId = receivedId;
		this.receivedBy = receivedBy;
	}
}
