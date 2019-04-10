package net.fashiongo.webadmin.model.pojo.bid;

import java.io.Serializable;
import java.util.List;

public class ListingAdBidSpot implements Serializable {
	private List<ListingAdBid> bidList;

	public List<ListingAdBid> getBidList() {
		return bidList;
	}
	public void setBidList(List<ListingAdBid> bidList) {
		this.bidList = bidList;
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
