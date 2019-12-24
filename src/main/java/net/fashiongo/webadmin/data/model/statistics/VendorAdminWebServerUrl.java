package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VendorAdminWebServerUrl {
    @JsonProperty(value = "adminwebserverid")
    private Integer adminwebserverid;

    @JsonProperty(value = "WholeSalerCount")
    private Long wholeSalerCount;

    @JsonProperty(value = "adminwebserverurl")
    private String adminwebserverurl;
}
