package net.fashiongo.webadmin.model.pojo.message.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SetMessageParameter {
	@JsonProperty("referenceid")
	private Integer referenceid;
	
	@JsonProperty("senderid")
	private Integer senderid;
	
	@JsonProperty("contactid")
	private Integer contactID;
	
	@JsonProperty("createdby")
	private String createdby;
	
	@JsonProperty("recipientid")
	private Integer recipientid;
	
	@JsonProperty("recipienttypeid")
	private Integer recipienttypeid;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("filename")
	private String filename;
	
	@JsonProperty("filename2")
	private String filename2;
	
	@JsonProperty("filename3")
	private String filename3;
	
	@JsonProperty("topic")
	private String topic;
	
	@JsonProperty("topreferenceid")
	private Integer topreferenceid;

	public Integer getReferenceid() {
		return referenceid;
	}

	public void setReferenceid(Integer referenceid) {
		this.referenceid = referenceid;
	}

	public Integer getSenderid() {
		return senderid;
	}

	public void setSenderid(Integer senderid) {
		this.senderid = senderid;
	}

	public Integer getContactID() {
		return contactID;
	}

	public void setContactID(Integer contactID) {
		this.contactID = contactID;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Integer getRecipientid() {
		return recipientid;
	}

	public void setRecipientid(Integer recipientid) {
		this.recipientid = recipientid;
	}

	public Integer getRecipienttypeid() {
		return recipienttypeid;
	}

	public void setRecipienttypeid(Integer recipienttypeid) {
		this.recipienttypeid = recipienttypeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename2() {
		return filename2;
	}

	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}

	public String getFilename3() {
		return filename3;
	}

	public void setFilename3(String filename3) {
		this.filename3 = filename3;
	}

	public Integer getTopic() {
		return topic == null ? 0 : Integer.parseInt(topic);
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getTopreferenceid() {
		return topreferenceid;
	}

	public void setTopreferenceid(Integer topreferenceid) {
		this.topreferenceid = topreferenceid;
	}
	
	
}
