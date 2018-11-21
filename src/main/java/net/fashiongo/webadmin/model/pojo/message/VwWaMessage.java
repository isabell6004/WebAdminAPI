package net.fashiongo.webadmin.model.pojo.message;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class VwWaMessage {
	@JsonProperty("MapID")
	private Integer mapID;
	
	@JsonProperty("RecipientTypeID")
	private Integer recipientTypeID;
	
	@JsonProperty("RecipientID")
	private Integer recipientID;
	
	@JsonProperty("MessageID")
	private Integer messageID;
	
	@JsonProperty("ReferenceID")
	private Integer referenceID;
	
	@JsonProperty("Title")
	private String Title;
	
	@JsonProperty("Body")
	private String body;
	
	@JsonProperty("SenderTypeID")
	private Integer senderTypeID;
	
	@JsonProperty("SenderID")
	private Integer senderID;
	
	@JsonProperty("SentOn")
	private LocalDateTime sentOn;
	
	@JsonProperty("ReadOn")
	private LocalDateTime readOn;
	
	@JsonProperty("VendorCompanyName")
	private String vendorCompanyName;
	
	@JsonProperty("RetailerCompanyName")
	private String retailerCompanyName;
	
	@JsonProperty("ReadBy")
	private String readBy;
	
	@JsonProperty("MessageCategoryID")
	private Integer messageCategoryID;
	
	@JsonProperty("CategoryName")
	private String categoryName;
	
	@JsonProperty("MessgaeGUID")
	private String messgaeGUID;
	
	@JsonProperty("AttachedFileName")
	private String attachedFileName;
	
	@JsonProperty("AttachedFileName2")
	private String attachedFileName2;
	
	@JsonProperty("AttachedFileName3")
	private String attachedFileName3;
	
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
	@JsonProperty("VendorEmail")
	private String vendorEmail;
	
	@JsonProperty("RetailerEmail")
	private String retailerEmail;
	
	
	@JsonProperty("IsDeletedBySender")
	private Boolean isDeletedBySender;
	
	@JsonProperty("IsDeletedByRecipient")
	private Boolean isDeletedByRecipient;

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
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
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
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

	public LocalDateTime getSentOn() {
		return sentOn;
	}

	public void setSentOn(LocalDateTime sentOn) {
		this.sentOn = sentOn;
	}

	public LocalDateTime getReadOn() {
		return readOn;
	}

	public void setReadOn(LocalDateTime readOn) {
		this.readOn = readOn;
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

	public String getReadBy() {
		return readBy;
	}

	public void setReadBy(String readBy) {
		this.readBy = readBy;
	}

	public Integer getMessageCategoryID() {
		return messageCategoryID;
	}

	public void setMessageCategoryID(Integer messageCategoryID) {
		this.messageCategoryID = messageCategoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getMessgaeGUID() {
		return messgaeGUID;
	}

	public void setMessgaeGUID(String messgaeGUID) {
		this.messgaeGUID = messgaeGUID;
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

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public String getVendorEmail() {
		return vendorEmail;
	}

	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	public String getRetailerEmail() {
		return retailerEmail;
	}

	public void setRetailerEmail(String retailerEmail) {
		this.retailerEmail = retailerEmail;
	}

	public Boolean getIsDeletedBySender() {
		return isDeletedBySender;
	}

	public void setIsDeletedBySender(Boolean isDeletedBySender) {
		this.isDeletedBySender = isDeletedBySender;
	}

	public Boolean getIsDeletedByRecipient() {
		return isDeletedByRecipient;
	}

	public void setIsDeletedByRecipient(Boolean isDeletedByRecipient) {
		this.isDeletedByRecipient = isDeletedByRecipient;
	}

	public Integer getRecipientTypeID() {
		return recipientTypeID;
	}

	public void setRecipientTypeID(Integer recipientTypeID) {
		this.recipientTypeID = recipientTypeID;
	}

	public Integer getRecipientID() {
		return recipientID;
	}

	public void setRecipientID(Integer recipientID) {
		this.recipientID = recipientID;
	}
	
}
