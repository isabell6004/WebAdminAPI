package net.fashiongo.webadmin.data.model.franchise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FranchiseSubAddParameter {

	@JsonProperty("retailerId")
	private Integer retailerId;

	@JsonProperty("masterAccountId")
	private Integer masterAccountId;
}
