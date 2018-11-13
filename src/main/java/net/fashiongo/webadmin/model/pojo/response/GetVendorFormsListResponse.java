package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.FashiongoForm;

/**
 * 
 * @author Reo
 *
 */
public class GetVendorFormsListResponse {
	@JsonProperty("Table")
	private List<FashiongoForm> fashiongoFormList;

	public List<FashiongoForm> getFashiongoFormList() {
		return fashiongoFormList;
	}

	public void setFashiongoFormList(List<FashiongoForm> fashiongoFormList) {
		this.fashiongoFormList = fashiongoFormList;
	}
	
	
}
