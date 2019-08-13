package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SitemgmtAdPageSpot {
    @JsonProperty("SpotID")
    private Integer spotID;

    @JsonProperty("SpotName")
    private String spotName;
}
