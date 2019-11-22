package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetLogEmailSentParameter {

	@JsonProperty(value = "retailerid")
	private Integer retailerid;

	@JsonProperty(value = "type")
	private Integer type;

	@JsonProperty(value = "companyname")
	private String companyname;

	@JsonProperty(value = "emailcontent")
	private String emailcontent;

	@JsonProperty(value = "referenceid")
	private Integer referenceid;

}
