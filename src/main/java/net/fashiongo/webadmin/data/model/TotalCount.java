package net.fashiongo.webadmin.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class TotalCount {

	public TotalCount() {
	}

	@Column(name = "Count")
	@JsonProperty("Count")
	private Integer count;

	public Integer getCount() {
		return count;
	}
}
