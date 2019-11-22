package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommunicationLogResponse {

	@JsonProperty("ContactedBy")
	private String contactedBy;

	@JsonProperty("CommunicatedOn")
	private LocalDateTime communicatedOn;

	@JsonProperty("CommunicationID")
	private Integer communicationID;

	@JsonProperty("ReasonID")
	private Integer reasonID;

	@JsonProperty("Reason")
	private String reason;

	@JsonProperty("Notes")
	private String notes;

	@JsonProperty("Confirmed1")
	private boolean confirmed1;

	@JsonProperty("Confirmed2")
	private boolean confirmed2;

	@JsonProperty("CheckedBy")
	private String checkedBy;

	@JsonProperty("CheckedBy2")
	private String checkedBy2;

	@JsonProperty("CheckedOn")
	private LocalDateTime checkedOn;

	@JsonProperty("CheckedOn2")
	private LocalDateTime checkedOn2;

}
