package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* @author Nayeon Kim
*/
public class TrendReportList {
	private Long row;
	
	@JsonProperty("TrendReportID")
	private Integer trendReportID;
	
	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("Image")
	private String image;
	
	@JsonProperty("SquareImage")
	private String squareImage;
	
	@JsonProperty("MiniImage")
	private String MiniImage;
	
	@JsonProperty("CuratedType")
	private Integer curatedType;
	
	@JsonProperty("TRDescription")
	private String tRDescription;
	
	@JsonProperty("DateFrom")
	private LocalDateTime dateFrom;
	
	@JsonProperty("DateTo")
	private LocalDateTime dateTo;
	
	@JsonProperty("ListOrder")
	private Integer listOrder;
	
	@JsonProperty("Active")
	private Boolean active;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("ItemCount")
	private Integer itemCount;
	
	@JsonProperty("KMMImage1")
	private String kMMImage1;
	
	@JsonProperty("KMMImage2")
	private String kMMImage2;
	
	@JsonProperty("Sticky")
	private Boolean sticky;

	private Integer showId;

	private Integer showScheduleId;

	@JsonProperty("ExternalURL")
	private String externalURL;	
	
	public Long getRow() {
		return row;
	}

	public void setRow(Long row) {
		this.row = row;
	}

	public Integer getTrendReportID() {
		return trendReportID;
	}

	public void setTrendReportID(Integer trendReportID) {
		this.trendReportID = trendReportID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSquareImage() {
		return squareImage;
	}

	public void setSquareImage(String squareImage) {
		this.squareImage = squareImage;
	}

	public String getMiniImage() {
		return MiniImage;
	}

	public void setMiniImage(String miniImage) {
		MiniImage = miniImage;
	}

	public Integer getCuratedType() {
		return curatedType;
	}

	public void setCuratedType(Integer curatedType) {
		this.curatedType = curatedType;
	}

	public String gettRDescription() {
		return tRDescription;
	}

	public void settRDescription(String tRDescription) {
		this.tRDescription = tRDescription;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public String getkMMImage1() {
		return kMMImage1;
	}

	public void setkMMImage1(String kMMImage1) {
		this.kMMImage1 = kMMImage1;
	}

	public String getkMMImage2() {
		return kMMImage2;
	}

	public void setkMMImage2(String kMMImage2) {
		this.kMMImage2 = kMMImage2;
	}
	
	public String getExternalURL() {
		return externalURL;
	}

	public void setExternalURL(String externalURL) {
		this.externalURL = externalURL;
	}

	
	public Boolean getSticky() {
		return sticky;
	}

	public void setSticky(Boolean sticky) {
		this.sticky = sticky;
	}

	public Integer getShowId() { return showId; }

	public void setShowId(Integer showId) { this.showId = showId; }

	public Integer getShowScheduleId() { return showScheduleId; }

	public void setShowScheduleId(Integer showScheduleId) { this.showScheduleId = showScheduleId; }
}
