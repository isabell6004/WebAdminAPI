package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetVendorStatParameter {
    @JsonProperty(value = "fromDate")
    private String fromDate;

    @JsonProperty(value = "toDate")
    private String toDate;

    @JsonProperty(value = "interval")
    private Integer interval;

    @JsonProperty(value = "wholesalerid")
    private Integer wholeSalerID;
}
