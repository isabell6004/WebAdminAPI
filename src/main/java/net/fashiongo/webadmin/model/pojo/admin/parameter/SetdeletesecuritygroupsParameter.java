package net.fashiongo.webadmin.model.pojo.admin.parameter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class SetdeletesecuritygroupsParameter {
	@ApiModelProperty(required = false, example="[17, 18, 19]")
	private String data;

	public void setData(String data) {
		this.data = data;
	}

	public List<Integer> getData() throws IOException {
		return StringUtils.isEmpty(data) ? null : new ObjectMapper().reader(List.class).readValue(data);
	}
}