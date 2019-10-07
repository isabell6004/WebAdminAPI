package net.fashiongo.webadmin.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListAccountDeactivationReason {

	@JsonProperty("ReasonID")
	private Integer reasonID;

	@JsonProperty("reason")
	private String reason;
}
