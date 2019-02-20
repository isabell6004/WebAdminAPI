package net.fashiongo.webadmin.model.pojo.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class MessageReply {
	@JsonProperty("MapID")
	private Integer mapID;
	
	@JsonProperty("RecipientID")
	private Integer recipientID;
	
	@JsonProperty("ReadOn")
	private LocalDateTime readOn;
	
	@JsonProperty("MessageID")
	private Integer messageID;
	
	@JsonProperty("ReferenceID")
	private Integer referenceID;
	
	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("Body")
	private String body;
	
	@JsonProperty("SenderTypeID")
	private Integer senderTypeID;
	
	@JsonProperty("SenderID")
	private Integer senderID;
	
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("VendorCompanyName")
	private String vendorCompanyName;
	
	@JsonProperty("RetailerCompanyName")
	private String retailerCompanyName;
	
	@JsonProperty("MessageGUID")
	private String messageGUID;
	
	@JsonProperty("AttachedFileName")
	private String attachedFileName;
	
	@JsonProperty("AttachedFileName2")
	private String attachedFileName2;
	
	@JsonProperty("AttachedFileName3")
	private String attachedFileName3;
	
	@JsonProperty("IsDeletedBySender")
	private Boolean IsDeletedBySender;

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getRecipientID() {
		return recipientID;
	}

	public void setRecipientID(Integer recipientID) {
		this.recipientID = recipientID;
	}

	public LocalDateTime getReadOn() {
		return readOn;
	}

	public void setReadOn(LocalDateTime readOn) {
		this.readOn = readOn;
	}

	public Integer getMessageID() {
		return messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	public Integer getReferenceID() {
		return referenceID;
	}

	public void setReferenceID(Integer referenceID) {
		this.referenceID = referenceID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getSenderTypeID() {
		return senderTypeID;
	}

	public void setSenderTypeID(Integer senderTypeID) {
		this.senderTypeID = senderTypeID;
	}

	public Integer getSenderID() {
		return senderID;
	}

	public void setSenderID(Integer senderID) {
		this.senderID = senderID;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getVendorCompanyName() {
		return vendorCompanyName;
	}

	public void setVendorCompanyName(String vendorCompanyName) {
		this.vendorCompanyName = vendorCompanyName;
	}

	public String getRetailerCompanyName() {
		return retailerCompanyName;
	}

	public void setRetailerCompanyName(String retailerCompanyName) {
		this.retailerCompanyName = retailerCompanyName;
	}

	public String getMessageGUID() {
		return messageGUID.toLowerCase();
	}

	public void setMessageGUID(String messageGUID) {
		this.messageGUID = messageGUID;
	}

	public String getAttachedFileName() {
		return attachedFileName;
	}

	public void setAttachedFileName(String attachedFileName) {
		this.attachedFileName = attachedFileName;
	}

	public String getAttachedFileName2() {
		return attachedFileName2;
	}

	public void setAttachedFileName2(String attachedFileName2) {
		this.attachedFileName2 = attachedFileName2;
	}

	public String getAttachedFileName3() {
		return attachedFileName3;
	}

	public void setAttachedFileName3(String attachedFileName3) {
		this.attachedFileName3 = attachedFileName3;
	}

	public Boolean getIsDeletedBySender() {
		return IsDeletedBySender;
	}

	public void setIsDeletedBySender(Boolean isDeletedBySender) {
		IsDeletedBySender = isDeletedBySender;
	}
	
}
