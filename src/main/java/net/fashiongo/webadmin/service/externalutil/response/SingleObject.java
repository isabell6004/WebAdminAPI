package net.fashiongo.webadmin.service.externalutil.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SingleObject<T> implements ApiResponseBody {

	private T content;

	public SingleObject() {
	}

	@Builder
	public SingleObject(T content) {
		this.content = content;
	}
}
