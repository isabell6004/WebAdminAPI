package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetBestItemsParamter {
    @JsonProperty(value = "pageno")
    private Integer pageno;

    @JsonProperty(value = "pagesize")
    private Integer pagesize;

    @JsonProperty(value = "top")
    private String top;

    @JsonProperty(value = "fromdate")
    private String fromDate;

    @JsonProperty(value = "todate")
    private String toDate;

    @JsonProperty(value = "statisticstype")
    private Integer statisticsType;

    @JsonProperty(value = "cateid")
    private Integer cateId;

    @JsonProperty(value = "subcateid")
    private Integer subCateId;

    @JsonProperty(value = "subsubcateid")
    private Integer subSubCateId;

    @JsonProperty(value = "wholesalerid")
    private Integer wholesalerId;

    @JsonProperty(value = "orderby")
    private String orderBy;
}
