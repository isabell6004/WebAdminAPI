/**
 * 
 */
package net.fashiongo.webadmin.model.response;

import java.util.List;

import net.fashiongo.webadmin.model.pojo.MessageList;
import net.fashiongo.webadmin.model.pojo.Total;

/**
 * @author Incheol Jung
 */
public class GetMessageResponse {
	private Total table;
	private List<MessageList> table1;
	
	public Total getTable() {
		return table;
	}
	public void setTable(Total table) {
		this.table = table;
	}
	public List<MessageList> getTable1() {
		return table1;
	}
	public void setTable1(List<MessageList> table1) {
		this.table1 = table1;
	}
}
