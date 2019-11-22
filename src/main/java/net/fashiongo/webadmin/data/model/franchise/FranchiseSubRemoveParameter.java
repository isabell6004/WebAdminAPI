package net.fashiongo.webadmin.data.model.franchise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class FranchiseSubRemoveParameter {

	@JsonProperty("retailerIds")
	private List<Integer> retailerIds;

	@JsonProperty("masterAccountId")
	private Integer masterAccountId;
}
