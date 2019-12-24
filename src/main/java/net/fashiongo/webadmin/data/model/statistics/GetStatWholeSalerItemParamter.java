package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetStatWholeSalerItemParamter {
    @JsonProperty(value = "adminWebServerID")
    private Integer adminWebServerID;

    @JsonProperty(value = "imageServerID")
    private Integer imageServerID;

    @JsonProperty(value = "vendorname")
    private String vendorname;

    @JsonProperty(value = "fromDate")
    private String fromDate;

    @JsonProperty(value = "toDate")
    private String toDate;
}
