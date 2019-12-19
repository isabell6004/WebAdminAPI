package net.fashiongo.webadmin.data.model.kmm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class KmmSavePayload {
    @JsonProperty(value = "trendReport")
    private KmmTrendReport trendReport;

    @JsonProperty(value = "contents")
    private String contents;

    @JsonProperty(value = "selectedItems")
    private List<Integer> selectedItems;
}
