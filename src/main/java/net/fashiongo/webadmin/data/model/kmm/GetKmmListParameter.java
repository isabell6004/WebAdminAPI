package net.fashiongo.webadmin.data.model.kmm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetKmmListParameter {
    @JsonProperty(value = "pagenum")
    private Integer pagenum;

    @JsonProperty(value = "pagesize")
    private Integer pagesize;

    @JsonProperty(value = "searchtxt")
    private String searchtxt;

    @JsonProperty(value = "fromdate")
    private String fromdate;

    @JsonProperty(value = "todate")
    private String todate;

    @JsonProperty(value = "active")
    private String active;

    @JsonProperty(value = "orderby")
    private String orderby;

    @JsonProperty(value = "orderbygubn")
    private String orderbygubn;

    @JsonProperty(value = "CuratedType")
    private Integer curatedType;
}
