package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StatReport {

	@JsonProperty("dt")
	private LocalDateTime dt;

	@JsonProperty("ordercount")
	private Integer ordercount;

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("qty")
	private Integer qty;

	@JsonProperty("wholesalercout")
	private Integer wholesalercout;

	@JsonProperty("retailercount")
	private Integer retailercount;
}
