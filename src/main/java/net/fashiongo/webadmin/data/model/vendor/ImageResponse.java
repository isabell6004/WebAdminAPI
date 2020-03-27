package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ImageResponse {
    @JsonProperty("contents")
    private List<VendorImage> imageList;

    @JsonProperty("totalCount")
    private Long total;

    public ImageResponse() {
    }

    @Builder
    public ImageResponse(List<VendorImage> bannerImageList, Long total) {
        this.imageList = bannerImageList;
        this.total = total;
    }
}
