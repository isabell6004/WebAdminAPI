package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMappingVendorAssigned {
	@JsonProperty("Assigned")
   private Integer assigned;

	public Integer getAssigned() {
		return assigned;
	}
	
	public void setAssigned(Integer assigned) {
		this.assigned = assigned;
	}
   
}
