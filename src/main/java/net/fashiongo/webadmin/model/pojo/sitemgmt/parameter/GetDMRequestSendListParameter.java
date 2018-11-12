package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author Incheol Jung
 */
public class GetDMRequestSendListParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(required = false, example="92008,91997,91996,91984")
	@JsonProperty("obj")
	private List<Integer> dmIds;
	
	public List<Integer> getDmIds() {
		return dmIds;
	}
	public void setDmIds(List<Integer> dmIds) {
		this.dmIds = dmIds;
	}
	
}
