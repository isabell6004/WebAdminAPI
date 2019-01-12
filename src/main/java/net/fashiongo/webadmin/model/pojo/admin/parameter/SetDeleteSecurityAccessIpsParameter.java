package net.fashiongo.webadmin.model.pojo.admin.parameter;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author DAHYE
 *
 */
@SuppressWarnings("serial")
public class SetDeleteSecurityAccessIpsParameter implements Serializable {
	@ApiModelProperty(required = false, example="[3,4]")
	@JsonProperty("data")
	private List<Integer> idList;

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
}
