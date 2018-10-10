package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.Resource;

public class GetSecurityResourcesResponse {
	@JsonProperty("Table")
	private List<Resource> resource;

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}
	
}