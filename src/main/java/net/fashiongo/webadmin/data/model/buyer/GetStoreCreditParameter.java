package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class GetStoreCreditParameter {

	@JsonProperty(value = "retailerid")
	private Integer retailerId;

	@JsonProperty(value = "vendor")
	private String vendor;

	@JsonProperty(value = "po")
	private String po;

	@JsonProperty(value = "from")
	private Date from;

	@JsonProperty(value = "to")
	private Date to;
}
