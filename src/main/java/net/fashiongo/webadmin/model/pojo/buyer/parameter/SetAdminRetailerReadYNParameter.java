package net.fashiongo.webadmin.model.pojo.buyer.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@Data
public class SetAdminRetailerReadYNParameter {
	@JsonProperty("obj")
	private List<Integer> obj;
	
	@JsonProperty("readyn")
	private Boolean readYN;
	
	public Boolean getReadYN() {
		return readYN == null ? true : readYN;
	}
}
