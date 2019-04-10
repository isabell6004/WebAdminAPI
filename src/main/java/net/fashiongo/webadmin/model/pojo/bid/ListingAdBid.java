package net.fashiongo.webadmin.model.pojo.bid;

import lombok.Builder;
import lombok.ToString;
import net.fashiongo.webadmin.model.primary.AdBid;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@ToString
public class ListingAdBid implements Comparable<ListingAdBid>, Serializable {
	
	private int bidId;
	private int bidSettingId;
	private int wid;
	private long bidAmount;
	private long maxBidAmount;
	private long currentBidAmount;
	private LocalDateTime biddedOn;
	private String biddedBy;

	/**
	 * whether currentBidAmount changed or not
 	 */
	private boolean modified = false;

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
	public long getMaxBidAmount() {
		return maxBidAmount;
	}
	public void setMaxBidAmount(long maxBidAmount) {
		this.maxBidAmount = maxBidAmount;
	}
	public long getCurrentBidAmount() {
		return currentBidAmount;
	}
	public void setCurrentBidAmount(long currentBidAmount) {
		this.currentBidAmount = currentBidAmount;
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
		return ListingAdBid.builder()
				.bidId(adBid.getBidId())
				.wid(adBid.getWholeSalerId())
				.bidSettingId(adBid.getBidSettingId())
				.bidAmount(adBid.getBidAmount().longValue())
				.currentBidAmount(adBid.getCurrentBidAmount().longValue())
				.maxBidAmount(adBid.getMaxBidAmount().longValue())
				.biddedOn(adBid.getBiddedOn())
				.biddedBy(adBid.getBiddedBy())
				.build();
	}

	@Override
	public int compareTo(ListingAdBid o) {
		int compare = Long.compare(o.currentBidAmount, this.currentBidAmount);
		if (compare == 0) {
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
}
