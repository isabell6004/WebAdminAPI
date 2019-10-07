package net.fashiongo.webadmin.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LogSentEmail {

	@JsonProperty("EmailType")
	private String emailType;

	@JsonProperty("EmailContents")
	private String emailContents;

	@JsonProperty("ReferenceText")
	private String referenceText;

	@JsonProperty("SentBy")
	private String sentBy;

	@JsonProperty("SentEmailTypeID")
	private Integer sentEmailTypeID;

	@JsonProperty("SentOn")
	private LocalDateTime sentOn;

}
