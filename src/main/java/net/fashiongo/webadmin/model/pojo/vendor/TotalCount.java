package net.fashiongo.webadmin.model.pojo.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalCount {
	@JsonProperty("Count")
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}