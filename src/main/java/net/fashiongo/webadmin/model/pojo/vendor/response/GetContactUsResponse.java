package net.fashiongo.webadmin.model.pojo.vendor.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.primary.ContactUs;

/**
 * 
 * @author Reo
 *
 */
public class GetContactUsResponse {
	@JsonProperty("Table")
	private List<Total> total;
	
	@JsonProperty("Table1")
	private List<ContactUs> contactUsList;

	public List<Total> getTotal() {
		return total;
	}

	public void setTotal(List<Total> total) {
		this.total = total;
	}

	public List<ContactUs> getContactUsList() {
		return contactUsList;
	}

	public void setContactUsList(List<ContactUs> contactUsList) {
		this.contactUsList = contactUsList;
	}

}
