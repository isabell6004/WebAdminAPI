package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FraudNoticeResponse {

	@JsonProperty("Active")
	private Boolean active;

	@JsonProperty("CommentByFG")
	private String commentByFG;

	@JsonProperty("CommentByWholeSaler")
	private String commentByWholeSaler;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("FraudDetail")
	private String fraudDetail;

	@JsonProperty("FraudNoticeID")
	private Integer fraudNoticeID;

	@JsonProperty("WholeSalerCompanyName")
	private String wholeSalerCompanyName;
}
