package net.fashiongo.webadmin.model.pojo.vendor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
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

	public Integer getVendorContractDocumentID() {
		return vendorContractDocumentID;
	}

	public void setVendorContractDocumentID(Integer vendorContractDocumentID) {
		this.vendorContractDocumentID = vendorContractDocumentID;
	}

	public Integer getVendorContractID() {
		return vendorContractID;
	}

	public void setVendorContractID(Integer vendorContractID) {
		this.vendorContractID = vendorContractID;
	}

	public Integer getDocumentTypeID() {
		return documentTypeID;
	}

	public void setDocumentTypeID(Integer documentTypeID) {
		this.documentTypeID = documentTypeID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public String getFileName3() {
		return fileName3;
	}

	public void setFileName3(String fileName3) {
		this.fileName3 = fileName3;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Boolean getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(Boolean checkBox) {
		this.checkBox = checkBox;
	}
	
	
}
