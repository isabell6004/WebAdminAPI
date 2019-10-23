package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DelStoreCreditParameter {

	@JsonProperty(value = "retailerid")
	private Integer retailerid;

	@JsonProperty(value = "creditid")
	private Integer creditid;
}
