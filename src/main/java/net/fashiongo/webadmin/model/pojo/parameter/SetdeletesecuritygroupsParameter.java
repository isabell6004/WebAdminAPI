package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

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