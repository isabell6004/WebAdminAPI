package net.fashiongo.webadmin.model.primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "Ad_Bid_Setting")
public class AdBidSetting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BidSettingID")
	private Integer bidSettingId;
	
	@Column(name = "SpotID")
	private Integer spotId;
	
	@Column(name = "FromDate")
	private LocalDateTime fromDate;
	
	@Column(name = "BidStartedOn")
	private LocalDateTime bidStartedOn;
	
	@Column(name = "BidEndedOn")
	private LocalDateTime bidEndedOn;
	
	@Column(name = "BidStartingPrice")
	private BigDecimal bidStartingPrice;
	
	@Column(name = "BidPriceUnit")
	private BigDecimal bidPriceUnit;
	
	@Column(name = "BuyItNowPrice")
	private BigDecimal buyItNowPrice;
	
	@Column(name = "FinalizedOn")
	private LocalDateTime finalizedOn;
	
	@Column(name = "FinalizedBy")
	private String finalizedBy;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "spotID", insertable = false, updatable = false)
	private AdPageSpot spot;

}
