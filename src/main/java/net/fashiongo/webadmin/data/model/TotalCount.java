package net.fashiongo.webadmin.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class TotalCount {

	public TotalCount() {
	}

	public TotalCount(Integer count) {
		this.count = count;
	}

	@Column(name = "Count")
	@JsonProperty("Count")
	private Integer count;

	public Integer getCount() {
		return count;
	}
}
