package net.fashiongo.webadmin.data.model.sitemgmt.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ShowCountResponse {
	@JsonProperty("totalCount")
	private Integer totalCount;

	@JsonProperty("totalAmount")
	private BigDecimal totalAmount;

	@JsonProperty("recCnt")
	private Integer recCnt;
}
