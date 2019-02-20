package net.fashiongo.webadmin.model.pojo.message.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.message.MessageReply;

public class GetMessageReplyResponse {
	@JsonProperty("Table")
	private List<MessageReply> messageReplyList;

	public List<MessageReply> getMessageReplyList() {
		return messageReplyList;
	}

	public void setMessageReplyList(List<MessageReply> messageReplyList) {
		this.messageReplyList = messageReplyList;
	}
	
}
