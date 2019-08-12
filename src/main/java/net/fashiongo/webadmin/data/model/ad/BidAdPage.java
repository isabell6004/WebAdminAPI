package net.fashiongo.webadmin.data.model.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BidAdPage {
    @JsonProperty("PageID")
    private Integer pageID;
    @JsonProperty("PageName")
    private String pageName;
}
