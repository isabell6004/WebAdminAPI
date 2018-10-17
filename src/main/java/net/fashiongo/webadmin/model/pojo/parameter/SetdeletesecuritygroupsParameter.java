package net.fashiongo.webadmin.model.pojo.parameter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Incheol Jung
 */
public class SetdeletesecuritygroupsParameter {
	String data;

	public List<Integer> getData() {
		return StringUtils.isEmpty(data) ? null : Arrays.stream(data.replaceAll("\\[(.*)\\]", "").split(",")).map(Integer::parseInt).collect(Collectors.toList());
	}
}