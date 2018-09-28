package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.SecurityAccessIps;;

public class GetSecurityAccessIpsResponse {
	@JsonProperty("Table")
	private List<SecurityAccessIps> total;

	public List<SecurityAccessIps> getTotal() {
		return total;
	}

	public void setTotal(List<SecurityAccessIps> total) {
		this.total = total;
	}
	
}
