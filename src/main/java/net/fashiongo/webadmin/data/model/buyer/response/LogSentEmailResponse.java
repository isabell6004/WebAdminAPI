package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.LogSentEmail;

import java.util.List;

@Getter
@Builder
public class LogSentEmailResponse {

	@JsonProperty("logemailsent")
	private List<LogSentEmail> logEmailSent;
}
