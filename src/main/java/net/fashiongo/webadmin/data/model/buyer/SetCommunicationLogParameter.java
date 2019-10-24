package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetCommunicationLogParameter {

	@JsonProperty(value = "retailerid")
	private Integer  retailerid;

	@JsonProperty(value = "reasonid")
	private Integer reasonid;

	@JsonProperty(value = "communicationid")
	private Integer communicationid;

	@JsonProperty(value = "companyname")
	private String companyname;

	@JsonProperty(value = "contactedby")
	private String contactedby;

	@JsonProperty(value = "communicatedon")
	private String communicatedon;

	@JsonProperty(value = "notes")
	private String notes;

	@JsonProperty(value = "confirmed1")
	private Boolean confirmed1;

	@JsonProperty(value = "confirmed2")
	private Boolean confirmed2;
}
