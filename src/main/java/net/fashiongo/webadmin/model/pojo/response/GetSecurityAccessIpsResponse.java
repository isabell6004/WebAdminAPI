package net.fashiongo.webadmin.model.pojo.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.SecurityAccessIp;;

@SuppressWarnings("serial")
public class GetSecurityAccessIpsResponse implements Serializable {
	@JsonProperty("Table")
	private List<SecurityAccessIp> ips;

	public List<SecurityAccessIp> getIps() {
		return ips;
	}

	public void setIps(List<SecurityAccessIp> ips) {
		this.ips = ips;
	}
}
