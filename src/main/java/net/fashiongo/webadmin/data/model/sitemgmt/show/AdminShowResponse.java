package net.fashiongo.webadmin.data.model.sitemgmt.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class AdminShowResponse<T> {
	@JsonProperty("Table")
	private List<ShowCountResponse> count;

	@JsonProperty("Table1")
	private List<T> contents;

	public AdminShowResponse(ShowCountResponse count, List<T> contents) {
		this.count = Collections.singletonList(count);
		this.contents = contents;
	}
}
