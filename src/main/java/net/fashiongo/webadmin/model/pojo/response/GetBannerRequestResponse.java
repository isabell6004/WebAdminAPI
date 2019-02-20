package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.vendor.TotalCount;
import net.fashiongo.webadmin.model.primary.VendorImageRequest;

/**
 * 
 * @author Reo
 *
 */
public class GetBannerRequestResponse {
	@JsonProperty("Table")
	private List<VendorImageRequest> bannerImageList;
	
	@JsonProperty("Table1")
	private List<TotalCount> total;

	public List<VendorImageRequest> getBannerImageList() {
		return bannerImageList;
	}

	public void setBannerImageList(List<VendorImageRequest> bannerImageList) {
		this.bannerImageList = bannerImageList;
	}

	public List<TotalCount> getTotalCount() {
		return total;
	}

	public void setTotalCount(List<TotalCount> total) {
		this.total = total;
	}
	
	
}
