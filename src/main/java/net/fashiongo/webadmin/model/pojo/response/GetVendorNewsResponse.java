package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.VendorNews;
/**
 * 
 * @author DAHYE
 *
 */
public class GetVendorNewsResponse {
	@JsonProperty("Table")
	private List<Total> total;
	
	@JsonProperty("Table1")
	private List<VendorNews> newsList;

	public List<Total> getTotal() {
		return total;
	}

	public List<VendorNews> getNewsList() {
		return newsList;
	}

	public void setTotal(List<Total> total) {
		this.total = total;
	}

	public void setNewsList(List<VendorNews> newsList) {
		this.newsList = newsList;
	}
	
	
}
