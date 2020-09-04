package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetBestItemsParamter {
    @JsonProperty(value = "pageno")
    private int pageno;

    @JsonProperty(value = "pagesize")
    private int pagesize;

    @JsonProperty(value = "top")
    private String top;

    @JsonProperty(value = "fromdate")
    private String fromDate;

    @JsonProperty(value = "todate")
    private String toDate;

    @JsonProperty(value = "statisticstype")
    private int statisticsType;

    @JsonProperty(value = "cateid")
    private int cateId;

    @JsonProperty(value = "subcateid")
    private int subCateId;

    @JsonProperty(value = "subsubcateid")
    private int subSubCateId;

    @JsonProperty(value = "wholesalerid")
    private int wholesalerId;

    @JsonProperty(value = "orderby")
    private String sortBy;
}
