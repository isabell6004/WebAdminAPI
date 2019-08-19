package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.sitemgmt.PolicyAgreement;

import java.util.List;

@Getter
@Builder
public class GetPolicyDetailResponse {

	@JsonProperty("Table")
	private List<Total> total;
	@JsonProperty("Table1")
	private List<PolicyAgreement> policyDetail;
}
