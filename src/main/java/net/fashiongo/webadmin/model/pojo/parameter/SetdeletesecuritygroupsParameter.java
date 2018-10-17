package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Incheol Jung
 */
public class SetdeletesecuritygroupsParameter {
	String data;

	public List<Integer> getData() throws IOException {
//		List<Integer> response = new Gson().fromJson(data, Integer.class);
//		List<Integer> l = new ObjectMapper().reader(List.class).readValue(data);
		return StringUtils.isEmpty(data) ? null : new ObjectMapper().reader(List.class).readValue(data);
	}
}