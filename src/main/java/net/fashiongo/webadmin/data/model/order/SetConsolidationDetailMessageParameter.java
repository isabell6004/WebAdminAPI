package net.fashiongo.webadmin.data.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetConsolidationDetailMessageParameter {

	@JsonProperty("orderid")
	private Integer orderId;

	@JsonProperty("consolidationid")
	private Integer consolidationId;

	@JsonProperty("wholesalerid")
	private Integer wholesalerId;

	@JsonProperty("newstitle")
	private String newsTitle;

	@JsonProperty("newscontent")
	private String newsContent;

	@JsonProperty("lastuser")
	private String lastUser;

	@JsonProperty("fromdate")
	private String fromDate;

	@JsonProperty("todate")
	private String toDate;

}
