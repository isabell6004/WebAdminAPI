package net.fashiongo.webadmin.service.externalutil.response;

import lombok.Getter;

@Getter
public class SingleObject<T> implements ApiResponseBody {

	private T content;
}
