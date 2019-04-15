package net.fashiongo.webadmin.model.pojo.bid;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ListingAdBidSpot implements Serializable {
	private List<ListingAdBid> bidList;

	public List<ListingAdBid> getBidList() {
		return bidList;
	}
	public void setBidList(List<ListingAdBid> bidList) {
		this.bidList = bidList;
	}

	public ListingAdBidSpot copy() {
		ListingAdBidSpot listingAdBidSpot = new ListingAdBidSpot();
		listingAdBidSpot.setBidList(bidList.stream()
				.map(listingAdBid -> ListingAdBid.builder()
						.bidId(listingAdBid.getBidId())
						.wid(listingAdBid.getWid())
						.bidSettingId(listingAdBid.getBidSettingId())
						.bidAmount(listingAdBid.getBidAmount())
						.maxBidAmount(listingAdBid.getMaxBidAmount())
						.biddedBy(listingAdBid.getBiddedBy())
						.biddedOn(listingAdBid.getBiddedOn())
						.modified(false)
						.build())
				.collect(Collectors.toList()));
		return listingAdBidSpot;
	}

	@Override
	public String toString() {
		if (bidList == null || bidList.size() == 0) {
			return "ListingAdBidSpot{" +
					"bidList=" + bidList +
					'}';
		} else {
			StringBuilder builder = new StringBuilder("ListingAdBidSpot{bidList=");
			int position = 1;
			for (ListingAdBid listingAdBid : bidList) {
				builder.append("\n\t").append(position++).append(". ").append(listingAdBid);
			}
			builder.append("\n}\n");
			return builder.toString();
		}
	}
}
