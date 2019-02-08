package net.fashiongo.webadmin.model.pojo.vendor.parameter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class DelVendorFormParameter implements Serializable{
	@JsonProperty("FashionGoFormIDs")
	private String fashionGoFormIDs;

	public String getFashionGoFormIDs() {
		return fashionGoFormIDs;
	}

	public void setFashionGoFormIDs(String fashionGoFormIDs) {
		this.fashionGoFormIDs = fashionGoFormIDs;
	}
	
}
