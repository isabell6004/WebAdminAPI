package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.message.Total;
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
	private List<Total> total;

	public List<VendorImageRequest> getBannerImageList() {
		return bannerImageList;
	}

	public void setBannerImageList(List<VendorImageRequest> bannerImageList) {
		this.bannerImageList = bannerImageList;
	}

	public List<Total> getTotal() {
		return total;
	}

	public void setTotal(List<Total> total) {
		this.total = total;
	}
	
	
}
