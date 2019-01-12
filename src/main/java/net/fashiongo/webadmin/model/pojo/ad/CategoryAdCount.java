package net.fashiongo.webadmin.model.pojo.ad;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Jiwon Kim
 */
public class CategoryAdCount {

	@JsonProperty("Count")
	@Column(name = "Count")
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
