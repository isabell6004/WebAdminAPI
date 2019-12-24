package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminServerProducts {
    @JsonProperty(value = "wholesalerid")
    private Integer wholesalerid;

    @JsonProperty(value = "companyname")
    private String companyname;

    @JsonProperty(value = "adminwebserverid")
    private Integer adminwebserverid;

    @JsonProperty(value = "imageserverid")
    private Integer imageserverid;

    @JsonProperty(value = "ItemCount")
    private Long itemCount;

    @JsonProperty(value = "pcnt")
    private Double pcnt;
}
