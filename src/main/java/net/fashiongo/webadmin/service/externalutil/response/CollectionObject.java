package net.fashiongo.webadmin.service.externalutil.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CollectionObject<T> implements ApiResponseBody {

	private List<T> contents;

	private Integer totalCount;
}
