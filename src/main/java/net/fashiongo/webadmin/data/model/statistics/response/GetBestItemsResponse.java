package net.fashiongo.webadmin.data.model.statistics.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.statistics.BestItems;

import java.util.List;

@Getter
@Builder
public class GetBestItemsResponse {
    @JsonProperty(value = "items")
    List<BestItems> bestItems;
}
