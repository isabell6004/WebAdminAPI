package net.fashiongo.webadmin.model.pojo.bid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.fashiongo.webadmin.model.primary.AdBid;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class ListingAdBid implements Comparable<ListingAdBid> {
	
	private int bidId;
	private int bidSettingId;
	private int wid;
	private long bidAmount;
	private long originalBidAmount;
	private Long maxBidAmount;
	private LocalDateTime biddedOn;
	private String biddedBy;

	/**
	 * whether bidAmount changed or not
 	 */
	@JsonIgnore
	private boolean modified = false;

	public ListingAdBid() {
	}

	public ListingAdBid(int bidId, int bidSettingId, int wid, long bidAmount, long originalBidAmount, Long maxBidAmount, LocalDateTime biddedOn, String biddedBy) {
		this.bidId = bidId;
		this.bidSettingId = bidSettingId;
		this.wid = wid;
		this.bidAmount = bidAmount;
		this.originalBidAmount = originalBidAmount;
		this.maxBidAmount = maxBidAmount;
		this.biddedOn = biddedOn;
		this.biddedBy = biddedBy;
	}

	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
	public int getBidSettingId() {
		return bidSettingId;
	}
	public void setBidSettingId(int bidSettingId) {
		this.bidSettingId = bidSettingId;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public long getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(long bidAmount) {
		this.bidAmount = bidAmount;
	}
	public Long getMaxBidAmount() {
		return maxBidAmount;
	}
	public void setMaxBidAmount(Long maxBidAmount) {
		this.maxBidAmount = maxBidAmount;
	}
	public LocalDateTime getBiddedOn() {
		return biddedOn;
	}
	public void setBiddedOn(LocalDateTime biddedOn) {
		this.biddedOn = biddedOn;
	}
	public String getBiddedBy() {
		return biddedBy;
	}
	public void setBiddedBy(String biddedBy) {
		this.biddedBy = biddedBy;
	}
	public boolean isModified() {
		return modified;
	}
	public void setModified(boolean modified) {
		this.modified = modified;
	}
	public long getOriginalBidAmount() {
		return originalBidAmount;
	}
	public void setOriginalBidAmount(long originalBidAmount) {
		this.originalBidAmount = originalBidAmount;
	}

	public static ListingAdBid of(AdBid adBid) {
		return new ListingAdBid(adBid.getBidId(), adBid.getBidSettingId(), adBid.getWholeSalerId(),
				adBid.getBidAmount().longValue(),
				adBid.getOriginBidAmount().longValue(),
				Optional.ofNullable(adBid.getMaxBidAmount()).map(BigDecimal::longValue).orElse(null),
				adBid.getBiddedOn(), adBid.getBiddedBy());
	}

	public static AdBid adBid(ListingAdBid listingAdBid, int statusId) {
		AdBid adBid = new AdBid();
		adBid.setBidId(listingAdBid.getBidId());
		adBid.setBidSettingId(listingAdBid.getBidSettingId());
		adBid.setWholeSalerId(listingAdBid.getWid());
		adBid.setBidAmount(BigDecimal.valueOf(listingAdBid.getBidAmount()));
		adBid.setOriginBidAmount(BigDecimal.valueOf(listingAdBid.getOriginalBidAmount()));
		adBid.setMaxBidAmount(Optional.ofNullable(listingAdBid.getMaxBidAmount()).map(BigDecimal::valueOf).orElse(null));
		adBid.setBiddedOn(listingAdBid.getBiddedOn());
		adBid.setBiddedBy(listingAdBid.getBiddedBy());
		adBid.setStatusId(statusId);
		return adBid;
	}

	@Override
	public int compareTo(ListingAdBid o) {
		int compare = Long.compare(o.bidAmount, this.bidAmount);
		if (compare == 0) {
			if (this.biddedOn.isEqual(o.biddedOn)) {
				if (this.getBidId() == 0) {
					return 1;
				} else if (o.getBidId() == 0) {
					return -1;
				}
				return this.getBidId() < o.getBidId() ? -1 : 1;
			}
			return this.biddedOn.compareTo(o.biddedOn);
		}
		return compare;
	}

	/**
	 * check bidIds
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ListingAdBid that = (ListingAdBid) o;
		return bidId == that.bidId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bidId);
	}

	@Override
	public String toString() {
		return "ListingAdBid{" +
				"bidId=" + bidId +
				", bidSettingId=" + bidSettingId +
				", wid=" + wid +
				", bidAmount=" + bidAmount +
				", originalBidAmount=" + originalBidAmount +
				", maxBidAmount=" + maxBidAmount +
				", biddedOn=" + biddedOn +
				", biddedBy='" + biddedBy + '\'' +
				'}';
	}
}
