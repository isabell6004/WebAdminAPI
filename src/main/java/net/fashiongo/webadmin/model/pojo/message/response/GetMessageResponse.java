/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.message.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.message.VwWaMessage;

/**
 * @author Incheol Jung
 */
public class GetMessageResponse {
	@JsonProperty("Table")
	private List<Total> total;
	
	@JsonProperty("Table1")
	private List<VwWaMessage> messagelist;
	
	public List<Total> getTotal() {
		return total;
	}
	public void setTotal(List<Total> total) {
		this.total = total;
	}
	public List<VwWaMessage> getMessagelist() {
		return messagelist;
	}
	public void setMessagelist(List<VwWaMessage> messagelist) {
		this.messagelist = messagelist;
	}

}
