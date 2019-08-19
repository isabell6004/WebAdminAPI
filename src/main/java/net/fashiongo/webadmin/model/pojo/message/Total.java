package net.fashiongo.webadmin.model.pojo.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Total {
	@JsonProperty("RecCnt")
	private Integer recCnt;

	public Integer getRecCnt() {
		return this.recCnt;
	}
}
