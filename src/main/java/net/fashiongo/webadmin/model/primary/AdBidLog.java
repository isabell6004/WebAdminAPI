package net.fashiongo.webadmin.model.primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ad_Bid_Log")
public class AdBidLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LogID")
	private Integer logId;
	
	@Column(name = "BidID")
	private Integer bidId;
	
	@Column(name = "BidSettingID")
	private Integer bidSettingId;
	
	@Column(name = "WholeSalerID")
	private Integer wholeSalerId;
	
	@Column(name ="BidAmount")
	private BigDecimal bidAmount;
	
	@Column(name = "BiddedOn")
	private LocalDateTime biddedOn;
	
	@Column(name = "StatusID")
	private Integer statusId;
	
	@Column(name = "MaxBidAmount")
	private BigDecimal maxBidAmount;
	
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Column(name = "BiddedBy")
	private String biddedBy;
	
	@Column(name = "OriginBidId")
	private Integer originBidId;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public Integer getBidSettingId() {
		return bidSettingId;
	}

	public void setBidSettingId(Integer bidSettingId) {
		this.bidSettingId = bidSettingId;
	}

	public Integer getWholeSalerId() {
		return wholeSalerId;
	}

	public void setWholeSalerId(Integer wholeSalerId) {
		this.wholeSalerId = wholeSalerId;
	}

	public BigDecimal getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(BigDecimal bidAmount) {
		this.bidAmount = bidAmount;
	}

	public LocalDateTime getBiddedOn() {
		return biddedOn;
	}

	public void setBiddedOn(LocalDateTime biddedOn) {
		this.biddedOn = biddedOn;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public BigDecimal getMaxBidAmount() {
		return maxBidAmount;
	}

	public void setMaxBidAmount(BigDecimal maxBidAmount) {
		this.maxBidAmount = maxBidAmount;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getBiddedBy() {
		return biddedBy;
	}

	public void setBiddedBy(String biddedBy) {
		this.biddedBy = biddedBy;
	}

	public Integer getOriginBidId() {
		return originBidId;
	}

	public void setOriginBidId(Integer originBidId) {
		this.originBidId = originBidId;
	}
	
}
