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
@Table(name = "Ad_Bid")
public class AdBid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Column(name = "FinalizedOn")
	private LocalDateTime finalizedOn;
	
	@Column(name = "FinalizedBy")
	private String finalizedBy;
	
	@Column(name = "MaxBidAmount")
	private BigDecimal maxBidAmount;
	
	@Column(name = "BiddedBy")
	private String biddedBy;
	
	@Column(name = "CurrentBidAmount")
	private BigDecimal currentBidAmount;
	
	@Column(name = "FinalizedBidAmount")
	private BigDecimal finalizedBidAmount;

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

	public LocalDateTime getFinalizedOn() {
		return finalizedOn;
	}

	public void setFinalizedOn(LocalDateTime finalizedOn) {
		this.finalizedOn = finalizedOn;
	}

	public String getFinalizedBy() {
		return finalizedBy;
	}

	public void setFinalizedBy(String finalizedBy) {
		this.finalizedBy = finalizedBy;
	}

	public BigDecimal getMaxBidAmount() {
		return maxBidAmount;
	}

	public void setMaxBidAmount(BigDecimal maxBidAmount) {
		this.maxBidAmount = maxBidAmount;
	}

	public String getBiddedBy() {
		return biddedBy;
	}

	public void setBiddedBy(String biddedBy) {
		this.biddedBy = biddedBy;
	}

	public BigDecimal getCurrentBidAmount() {
		return currentBidAmount;
	}

	public void setCurrentBidAmount(BigDecimal currentBidAmount) {
		this.currentBidAmount = currentBidAmount;
	}

	public BigDecimal getFinalizedBidAmount() {
		return finalizedBidAmount;
	}

	public void setFinalizedBidAmount(BigDecimal finalizedBidAmount) {
		this.finalizedBidAmount = finalizedBidAmount;
	}
	
}
