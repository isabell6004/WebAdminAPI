package net.fashiongo.webadmin.model.pojo.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.DMRequest;

/**
 * 
 * @author Incheol Jung
 */
public class GetDMRequestResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Table")
	private List<DMRequest> dmList;

	public List<DMRequest> getDmList() {
		return dmList;
	}

	public void setDmList(List<DMRequest> dmList) {
		this.dmList = dmList;
	}
	
}