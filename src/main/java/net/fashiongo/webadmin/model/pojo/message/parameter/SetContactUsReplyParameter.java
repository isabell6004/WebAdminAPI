package net.fashiongo.webadmin.model.pojo.message.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SetContactUsReplyParameter {
	@JsonProperty("contactid")
	private Integer contactID;
	
	@JsonProperty("repliedby")
	private String repliedBy;
	
	@JsonProperty("reply")
	private String reply;

	public Integer getContactID() {
		return contactID;
	}

	public void setContactID(Integer contactID) {
		this.contactID = contactID;
	}

	public String getRepliedBy() {
		return repliedBy;
	}

	public void setRepliedBy(String repliedBy) {
		this.repliedBy = repliedBy;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	
	
}
