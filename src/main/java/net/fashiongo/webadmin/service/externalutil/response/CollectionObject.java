package net.fashiongo.webadmin.service.externalutil.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CollectionObject<T> implements ApiResponseBody {

	private List<T> contents;

	private Integer totalCount;

	public CollectionObject() {
	}

	@Builder
	public CollectionObject(List<T> contents, Integer totalCount) {
		this.contents = contents;
		this.totalCount = totalCount;
	}
}
