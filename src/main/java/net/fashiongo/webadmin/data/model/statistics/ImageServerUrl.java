package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageServerUrl {
    @JsonProperty(value = "imageserverid")
    private Integer imageserverid;

    @JsonProperty(value = "WholeSalerCount")
    private Long wholeSalerCount;

    @JsonProperty(value = "ImageServerName")
    private String imageServerName;

    @JsonProperty(value = "UrlPath")
    private String urlPath;
}
