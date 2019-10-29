package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SendBuyerEmailParameter {

	@JsonProperty("type")
	private String type;

	@JsonProperty("username")
	private String username;

	@JsonProperty("reason")
	private String reason;

	@JsonProperty("d")
	private String d;

	@JsonProperty("retailerid")
	private Integer retailerid;

	@JsonProperty("recipient")
	private String recipient;

	@JsonProperty("title")
	private String title;

	@JsonProperty("content")
	private String content;

}
