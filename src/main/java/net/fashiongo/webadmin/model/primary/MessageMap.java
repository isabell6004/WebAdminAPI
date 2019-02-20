package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "Message_Map")
public class MessageMap {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "MapID")
	@JsonProperty("MapID")
	private Integer mapID;
	
	@Column(name = "MessageID")
	@JsonProperty("MessageID")
	private Integer messageID;
	
	@Column(name = "RecipientID")
	@JsonProperty("RecipientID")
	private Integer recipientID;
	
	@Column(name = "ReadOn")
	@JsonProperty("ReadOn")
	private LocalDateTime readOn;
	
	@Column(name = "IsDeletedByRecipient")
	@JsonProperty("IsDeletedByRecipient")
	private Boolean isDeletedByRecipient;
	
	@Column(name = "RecipientTypeID")
	@JsonProperty("RecipientTypeID")
	private Integer recipientTypeID;

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
	
	
}
