package net.fashiongo.webadmin.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Column;

@Builder
@AllArgsConstructor
public class Total {

	public Total() {
	}

	@Column(name = "RecCnt")
	@JsonProperty("RecCnt")
	private Integer recCnt;

	public Integer getRecCnt() {
		return recCnt;
	}
}
