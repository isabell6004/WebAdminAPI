package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListCommunicationReasonResponse {

	@JsonProperty("ReasonID")
	private Integer reasonID;

	@JsonProperty("Reason")
	private String reason;

	@JsonProperty("ParentID")
	private Integer parentID;

	@JsonProperty("Active")
	private boolean active;
}
