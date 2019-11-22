package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.ListAccountDeactivationReason;
import net.fashiongo.webadmin.data.model.LogSentEmail;
import net.fashiongo.webadmin.data.model.buyer.Retailer;

import java.util.List;

@Getter
@Builder
public class RetailerDetailResponse {

	@JsonProperty("deactivationreason")
	private List<ListAccountDeactivationReason> deactivationReason;

	@JsonProperty("logemailsent")
	private List<LogSentEmail> logEmailSent;

	@JsonProperty("retailer")
	private Retailer retailer;

}
