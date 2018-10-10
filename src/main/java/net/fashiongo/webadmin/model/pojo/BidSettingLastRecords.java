package net.fashiongo.webadmin.model.pojo;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BidSettingLastRecords {

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
	private String fromDate;
	
	@JsonProperty("WeekDateName")
	@Column(name = "AdDateName")
	private String adDateName;
	
	@JsonProperty("BidStartedOn")
	@Column(name = "BidStartedOn")
	private String bidStartedOn;
	
	@JsonProperty("BidEndedOn")
	@Column(name = "BidEndedOn")
	private String bidEndedOn;
	
	@JsonProperty("BidStartingPrice")
	@Column(name = "BidStartingPrice")
	private String bidStartingPrice;
	
	@JsonProperty("BidPriceUnit")
	@Column(name = "BidPriceUnit")
	private String bidPriceUnit;
	
	@JsonProperty("BuyItNowPrice")
	@Column(name = "BuyItNowPrice")
	private String buyItNowPrice;
	
	@JsonProperty("BidSettingID")
	private Integer bidSettingID;
	
	@JsonProperty("EmptySpotCnt")
	private Integer emptySpotCnt;
	
	@JsonProperty("BidAcceptedCnt")
	private Integer bidAcceptedCnt;
	
	@JsonProperty("PurchasedCnt")
	private Integer purchasedCnt;
	
	@JsonProperty("SpotInstanceCount")
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
		if (fromDate != null) {
			SimpleDateFormat convertDate = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat convertString = new SimpleDateFormat("MM/dd/yyyy");
			try {
				fromDate = convertString.format(convertDate.parse(fromDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getAdDateName() {
		return adDateName.substring(0, 3);
	}
	public void setAdDateName(String adDateName) {
		this.adDateName = adDateName;
	}
	public String getBidStartedOn() {
		if (bidStartedOn!=null) {
			SimpleDateFormat convertDate = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			SimpleDateFormat convertString = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			try {
				bidStartedOn = convertString.format(convertDate.parse(bidStartedOn));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bidStartedOn;
	}
	public void setBidStartedOn(String bidStartedOn) {
		this.bidStartedOn = bidStartedOn;
	}
	public String getBidEndedOn() {
		if (bidEndedOn!=null) {
			SimpleDateFormat convertDate = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			SimpleDateFormat convertString = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			try {
				bidEndedOn = convertString.format(convertDate.parse(bidEndedOn));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bidEndedOn;
	}
	public void setBidEndedOn(String bidEndedOn) {
		this.bidEndedOn = bidEndedOn;
	}
	public String getBidStartingPrice() {
		if (bidStartingPrice != null) {
			DecimalFormat df = new DecimalFormat("###.###");
			bidStartingPrice = df.format(bidStartingPrice);
		}
		return bidStartingPrice;
	}
	public void setBidStartingPrice(String bidStartingPrice) {
		this.bidStartingPrice = bidStartingPrice;
	}
	public String getBidPriceUnit() {
		if (bidPriceUnit != null) {
			DecimalFormat df = new DecimalFormat("###.###");
			bidPriceUnit = df.format(bidPriceUnit);
		}
		return bidPriceUnit;
	}
	public void setBidPriceUnit(String bidPriceUnit) {
		this.bidPriceUnit = bidPriceUnit;
	}
	public String getBuyItNowPrice() {
		if (buyItNowPrice != null) {
			DecimalFormat df = new DecimalFormat("###.###");
			buyItNowPrice = df.format(buyItNowPrice);
		}
		return buyItNowPrice;
	}
	public void setBuyItNowPrice(String buyItNowPrice) {
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
