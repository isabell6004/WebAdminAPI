/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.Total;

/**
 * @author Incheol Jung
 */
public class GetMessageResponse {
	@JsonProperty("Table")
	private List<Total> total;
	
	@JsonProperty("Table1")
	private List<Message> messagelist;
	
	public List<Total> getTotal() {
		return total;
	}
	public void setTotal(List<Total> total) {
		this.total = total;
	}
	public List<Message> getMessagelist() {
		return messagelist;
	}
	public void setMessagelist(List<Message> messagelist) {
		this.messagelist = messagelist;
	}
}
