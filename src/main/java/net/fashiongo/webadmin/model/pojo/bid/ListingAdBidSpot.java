package net.fashiongo.webadmin.model.pojo.bid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListingAdBidSpot {
	private List<ListingAdBid> bidList;

	public ListingAdBidSpot() {
		this.bidList = new ArrayList<>();
	}

	public ListingAdBidSpot(List<ListingAdBid> bidList) {
		this.bidList = bidList;
	}

	public List<ListingAdBid> getBidList() {
		return bidList;
	}
	public void setBidList(List<ListingAdBid> bidList) {
		this.bidList = bidList;
	}

	public ListingAdBidSpot copy() {
		return new ListingAdBidSpot(Optional.ofNullable(bidList)
				.map(ArrayList::new) // copy to new ArrayList
				.orElseGet(ArrayList::new));
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
