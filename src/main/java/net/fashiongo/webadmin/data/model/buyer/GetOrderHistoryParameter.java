package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetOrderHistoryParameter {

	@JsonProperty("pagenum")
	private Integer pageNum;

	@JsonProperty("pagesize")
	private Integer pageSize;

	@JsonProperty("retailerid")
	private Integer retailerID;

	@JsonProperty("wholecompanyname")
	private String wholeCompanyName;

	@JsonProperty("ponumber")
	private String poNumber;

	@JsonProperty("productname")
	private String productName;

	@JsonProperty("paymentstatusid")
	private Integer paymentStatusID;

	@JsonProperty("orderstatusid")
	private Integer orderStatusID;

	@JsonProperty("datefrom")
	private String dateFrom;

	@JsonProperty("dateto")
	private String dateTo;

	@JsonProperty("orderby")
	private String orderBy;

}
