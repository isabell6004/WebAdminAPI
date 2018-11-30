package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "Message")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "MessageID")
	@JsonProperty("MessageID")
	private Integer messageID;
	
	@Column(name = "ReferenceID")
	@JsonProperty("ReferenceID")
	private Integer referenceID;
	
	@Column(name = "Title")
	@JsonProperty("Title")
	private String title;
	
	@Column(name = "Body")
	@JsonProperty("Body")
	private String body;
	
	@Column(name = "SenderTypeID")
	@JsonProperty("SenderTypeID")
	private Integer senderTypeID;
	
	@Column(name = "SenderID")
	@JsonProperty("SenderID")
	private Integer senderID;	
	
	@Column(name = "CreatedOn")
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "CreatedBy")
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@Column(name = "ModifiedOn")
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@Column(name = "ModifiedBy")
	@JsonProperty("ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "IsDeletedBySender")
	@JsonProperty("IsDeletedBySender")
	private Integer isDeletedBySender;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private Boolean active;
	
	@Column(name = "AttachedFileName")
	@JsonProperty("AttachedFileName")
	private String attachedFileName;
	
	@Column(name = "AttachedFileName2")
	@JsonProperty("AttachedFileName2")
	private String attachedFileName2;
	
	@Column(name = "AttachedFileName3")
	@JsonProperty("AttachedFileName3")
	private String attachedFileName3;
	
	@Column(name = "MessageCategoryID")
	@JsonProperty("MessageCategoryID")
	private Integer messageCategoryID;
	
	@Column(name = "MessageGUID")
	@JsonProperty("MessageGUID")
	private String messageGUID;
	
	@Column(name = "SortModifiedOn")
	@JsonProperty("SortModifiedOn")
	private LocalDateTime SortModifiedOn;
	
	@Column(name = "TopReferenceID")
	@JsonProperty("TopReferenceID")
	private Integer topReferenceID;
	
	@Column(name = "IsFavorite")
	@JsonProperty("IsFavorite")
	private Integer isFavorite;
	
	@Column(name = "UpdatedOn")
	@JsonProperty("UpdatedOn")
	private LocalDateTime updatedOn;
	
	@Column(name = "HasNewReply")
	@JsonProperty("HasNewReply")
	private Boolean hasNewReply;

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

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getIsDeletedBySender() {
		return isDeletedBySender;
	}

	public void setIsDeletedBySender(Integer isDeletedBySender) {
		this.isDeletedBySender = isDeletedBySender;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	public Integer getMessageCategoryID() {
		return messageCategoryID;
	}

	public void setMessageCategoryID(Integer messageCategoryID) {
		this.messageCategoryID = messageCategoryID;
	}

	public String getMessageGUID() {
		return messageGUID;
	}

	public void setMessageGUID(String messageGUID) {
		this.messageGUID = messageGUID;
	}

	public LocalDateTime getSortModifiedOn() {
		return SortModifiedOn;
	}

	public void setSortModifiedOn(LocalDateTime sortModifiedOn) {
		SortModifiedOn = sortModifiedOn;
	}

	public Integer getTopReferenceID() {
		return StringUtils.isEmpty(topReferenceID) ? 0 : topReferenceID;
	}

	public void setTopReferenceID(Integer topReferenceID) {
		this.topReferenceID = topReferenceID;
	}

	public Integer getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Integer isFavorite) {
		this.isFavorite = isFavorite;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getHasNewReply() {
		return hasNewReply;
	}

	public void setHasNewReply(Boolean hasNewReply) {
		this.hasNewReply = hasNewReply;
	}
	
	
}
