package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class TrendReport {

	@JsonProperty("Active")
	private Boolean active;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("DateFrom")
	private LocalDateTime dateFrom;

	@JsonProperty("DateTo")
	private LocalDateTime dateTo;

	@JsonProperty("Image")
	private String image;

	@JsonProperty("ItemCount")
	private Integer itemCount;

	@JsonProperty("ListOrder")
	private Integer listOrder;

	@JsonProperty("Title")
	private String title;

	@JsonProperty("TrendReportID")
	private Integer trendReportID;

	@JsonProperty("row")
	private Integer row;

	public TrendReport(Boolean active, String createdBy, Timestamp dateFrom, Timestamp dateTo, String image, Long itemCount, Integer listOrder, String title, Integer trendReportID, Long row) {
		this.active = active;
		this.createdBy = createdBy;
		this.dateFrom = Optional.ofNullable(dateFrom).map(Timestamp::toLocalDateTime).orElse(null);
		this.dateTo = Optional.ofNullable(dateTo).map(Timestamp::toLocalDateTime).orElse(null);
		this.image = image;
		this.itemCount = Optional.ofNullable(itemCount).map(Long::intValue).orElse(null);
		this.listOrder = listOrder;
		this.title = title;
		this.trendReportID = trendReportID;
		this.row = Optional.ofNullable(row).map(Long::intValue).orElse(null);
	}
}
