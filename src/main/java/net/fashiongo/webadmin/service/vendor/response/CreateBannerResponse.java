package net.fashiongo.webadmin.service.vendor.response;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.service.externalutil.response.ApiResponseBody;

@Getter
@Setter
public class CreateBannerResponse implements ApiResponseBody {
    private Integer content;
}
