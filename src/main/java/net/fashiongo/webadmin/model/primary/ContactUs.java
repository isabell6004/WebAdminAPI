package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "ContactUs")
public class ContactUs {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("ContactID")
	@Column(name = "ContactID")
	private Integer contactID;
	
	@JsonProperty("Name")
	@Column(name = "Name")
	private String name;
	
	@JsonProperty("Email")
	@Column(name = "Email")
	private String email;
	
	@JsonProperty("Subject")
	@Column(name = "Subject")
	private String subject;
	
	@JsonProperty("Message")
	@Column(name = "Message")
	private String message;
	
	@JsonProperty("SessionID")
	@Column(name = "SessionID")
	private String sessionID;
	
	@JsonProperty("IPAddress")
	@Column(name = "IPAddress")
	private String iPAddress;
	
	@JsonProperty("CreatedOn")
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonProperty("Reply")
	@Column(name = "Reply")
	private String reply;
	
	@JsonProperty("RepliedOn")
	@Column(name = "RepliedOn")
	private LocalDateTime repliedOn;
	
	@JsonProperty("RepliedBy")
	@Column(name = "RepliedBy")
	private String repliedBy;

	public Integer getContactID() {
		return contactID;
	}

	public void setContactID(Integer contactID) {
		this.contactID = contactID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getiPAddress() {
		return iPAddress;
	}

	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public LocalDateTime getRepliedOn() {
		return repliedOn;
	}

	public void setRepliedOn(LocalDateTime repliedOn) {
		this.repliedOn = repliedOn;
	}

	public String getRepliedBy() {
		return repliedBy;
	}

	public void setRepliedBy(String repliedBy) {
		this.repliedBy = repliedBy;
	}
	
	
}
