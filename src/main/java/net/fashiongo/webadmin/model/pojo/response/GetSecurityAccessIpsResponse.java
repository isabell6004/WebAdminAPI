package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.SecurityAccessIp;;

public class GetSecurityAccessIpsResponse {
	@JsonProperty("Table")
	private List<SecurityAccessIp> ips;

	public List<SecurityAccessIp> getIps() {
		return ips;
	}

	public void setIps(List<SecurityAccessIp> ips) {
		this.ips = ips;
	}
}
