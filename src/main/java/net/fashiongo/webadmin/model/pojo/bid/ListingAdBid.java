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

	public ListingAdBid(int bidId, int bidSettingId, int wid, long bidAmount, Long maxBidAmount, LocalDateTime biddedOn, String biddedBy) {
		this.bidId = bidId;
		this.bidSettingId = bidSettingId;
		this.wid = wid;
		this.bidAmount = bidAmount;
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

	public static ListingAdBid of(AdBid adBid) {
		return new ListingAdBid(adBid.getBidId(), adBid.getBidSettingId(), adBid.getWholeSalerId(),
				adBid.getBidAmount().longValue(),
				Optional.ofNullable(adBid.getMaxBidAmount()).map(BigDecimal::longValue).orElse(null),
				adBid.getBiddedOn(), adBid.getBiddedBy());
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
				", maxBidAmount=" + maxBidAmount +
				", biddedOn=" + biddedOn +
				", biddedBy='" + biddedBy + '\'' +
				'}';
	}
}
