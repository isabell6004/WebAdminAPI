package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class HotSearchKeyword {

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("cnt")
	private Long cnt;

	public HotSearchKeyword(String dt, Long cnt) {
		this.dt = dt;
		this.cnt = cnt;
	}
}
