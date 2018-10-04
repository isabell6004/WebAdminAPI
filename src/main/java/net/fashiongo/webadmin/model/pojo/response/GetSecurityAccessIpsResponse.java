package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.SecurityAccessIps;;

public class GetSecurityAccessIpsResponse {
	@JsonProperty("Table")
	private List<SecurityAccessIps> ips;

	public List<SecurityAccessIps> getIps() {
		return ips;
	}

	public void setIps(List<SecurityAccessIps> ips) {
		this.ips = ips;
	}
}
