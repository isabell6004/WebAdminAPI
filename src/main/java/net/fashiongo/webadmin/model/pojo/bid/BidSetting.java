package net.fashiongo.webadmin.model.pojo.bid;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BidSetting {
	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private Integer spotID;

	@JsonProperty("SpotName")
	@Column(name = "SpotName")
	private String spotName;

	@JsonProperty("PageID")
	@Column(name = "PageID")
	private Integer pageID;

	@JsonProperty("PageName")
	@Column(name = "PageName")
	private String pageName;

	@JsonProperty("FromDate")
	@Column(name = "FromDate")
	private LocalDateTime fromDate;

	@JsonProperty("WeekDateName")
	@Column(name = "AdDateName")
	private String adDateName;

	@JsonProperty("BidStartedOn")
	@Column(name = "BidStartedOn")
	private LocalDateTime bidStartedOn;

	@JsonProperty("BidEndedOn")
	@Column(name = "BidEndedOn")
	private LocalDateTime bidEndedOn;

	@JsonProperty("BidStartingPrice")
	@Column(name = "BidStartingPrice")
	private BigDecimal bidStartingPrice;

	@JsonProperty("BidPriceUnit")
	@Column(name = "BidPriceUnit")
	private BigDecimal bidPriceUnit;

	@JsonProperty("BuyItNowPrice")
	@Column(name = "BuyItNowPrice")
	private BigDecimal buyItNowPrice;

	@JsonProperty("BidSettingID")
	@Column(name = "BidSettingID")
	private Integer bidSettingID;

	@JsonProperty("EmptySpotCnt")
	@Column(name = "EmptySpotCnt")
	private Integer emptySpotCnt;

	@JsonProperty("BidAcceptedCnt")
	@Column(name = "BidAcceptedCnt")
	private Integer bidAcceptedCnt;

	@JsonProperty("PurchasedCnt")
	@Column(name = "PurchasedCnt")
	private Integer purchasedCnt;

	@JsonProperty("SpotInstanceCount")
	@Column(name = "SpotInstanceCount")
	private Integer spotInstanceCount;

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public Integer getPageID() {
		return pageID;
	}

	public void setPageID(Integer pageID) {
		this.pageID = pageID;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getFromDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return fromDate.format(formatter);
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public String getAdDateName() {
		return adDateName.substring(0, 3);
	}

	public void setAdDateName(String adDateName) {
		this.adDateName = adDateName;
	}

	public String getBidStartedOn() {
		if (bidStartedOn == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		return bidStartedOn.format(formatter);
	}

	public void setBidStartedOn(LocalDateTime bidStartedOn) {
		this.bidStartedOn = bidStartedOn;
	}

	public String getBidEndedOn() {
		if (bidEndedOn == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		return bidEndedOn.format(formatter);
	}

	public void setBidEndedOn(LocalDateTime bidEndedOn) {
		this.bidEndedOn = bidEndedOn;
	}

	public String getBidStartingPrice() {
		if (bidStartingPrice == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("###.###");
		return df.format(bidStartingPrice);
	}

	public void setBidStartingPrice(BigDecimal bidStartingPrice) {
		this.bidStartingPrice = bidStartingPrice;
	}

	public String getBidPriceUnit() {
		if (bidPriceUnit == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("###.###");
		return df.format(bidPriceUnit);
	}

	public void setBidPriceUnit(BigDecimal bidPriceUnit) {
		this.bidPriceUnit = bidPriceUnit;
	}

	public String getBuyItNowPrice() {
		if (buyItNowPrice == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("###.###");
		return df.format(buyItNowPrice);
	}

	public void setBuyItNowPrice(BigDecimal buyItNowPrice) {
		this.buyItNowPrice = buyItNowPrice;
	}

	public Integer getBidSettingID() {
		return bidSettingID;
	}

	public void setBidSettingID(Integer bidSettingID) {
		this.bidSettingID = bidSettingID;
	}

	public Integer getEmptySpotCnt() {
		return emptySpotCnt;
	}

	public void setEmptySpotCnt(Integer emptySpotCnt) {
		this.emptySpotCnt = emptySpotCnt;
	}

	public Integer getBidAcceptedCnt() {
		return bidAcceptedCnt;
	}

	public void setBidAcceptedCnt(Integer bidAcceptedCnt) {
		this.bidAcceptedCnt = bidAcceptedCnt;
	}

	public Integer getPurchasedCnt() {
		return purchasedCnt;
	}

	public void setPurchasedCnt(Integer purchasedCnt) {
		this.purchasedCnt = purchasedCnt;
	}

	public Integer getSpotInstanceCount() {
		return spotInstanceCount;
	}

	public void setSpotInstanceCount(Integer spotInstanceCount) {
		this.spotInstanceCount = spotInstanceCount;
	}
}
