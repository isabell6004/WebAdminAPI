package net.fashiongo.webadmin.data.model.franchise.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.franchise.FranchiseSubAccount;

import java.util.List;

@Getter
@Builder
public class FranchiseMasterSubAccountsResponse {

	@JsonProperty("subAccounts")
	List<FranchiseSubAccount> subAccounts;

	@JsonProperty("totalCount")
	private Long totalCount;
}
