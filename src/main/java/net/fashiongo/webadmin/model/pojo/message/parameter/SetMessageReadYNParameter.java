package net.fashiongo.webadmin.model.pojo.message.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SetMessageReadYNParameter {
	@JsonProperty("messageid")
	private Integer messageID;
	
	@JsonProperty("readyn")
	private Boolean readYn;
	
	@JsonProperty("hasReplyYN")
	private Boolean hasReplyYN;

	public Integer getMessageID() {
		return messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	public Boolean getReadYn() {
		return readYn;
	}

	public void setReadYn(Boolean readYn) {
		this.readYn = readYn;
	}

	public Boolean getHasReplyYN() {
		return hasReplyYN == null ? false : hasReplyYN;
	}

	public void setHasReplyYN(Boolean hasReplyYN) {
		this.hasReplyYN = hasReplyYN;
	}
	
}