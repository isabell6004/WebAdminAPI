package net.fashiongo.webadmin.data.model.franchise.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FranchiseMasterResponse {

	@JsonProperty("franchiseMasterAccountId")
	private Integer franchiseMasterAccountId;

	@JsonProperty("retailerId")
	private Integer retailerId;

	@JsonProperty("createdOn")
	private LocalDateTime createdOn;

	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("totalSubAccounts")
	private Long totalSubAccounts;
}
