package net.fashiongo.webadmin.service.externalutil.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jinwoo on 2020-01-31.
 */
@Getter
@Setter
public class FashionGoApiResponse<T> {
    private ApiResponseHeader header;
    private T data;
}
