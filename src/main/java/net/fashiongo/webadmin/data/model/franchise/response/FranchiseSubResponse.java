package net.fashiongo.webadmin.data.model.franchise.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FranchiseSubResponse {

	@JsonProperty("franchiseMasterAccountId")
	private Integer franchiseMasterAccountId;

	@JsonProperty("franchiseSubAccountId")
	private Integer franchiseSubAccountId;

	@JsonProperty("retailerId")
	private Integer retailerId;

	@JsonProperty("companyName")
	private String companyName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("firstName")
	private String firstName;
}
