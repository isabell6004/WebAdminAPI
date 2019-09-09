package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.FeaturedItemList;

import java.util.List;

@Getter
@Builder
public class GetFeaturedItemListDayResponse {
    @JsonProperty("Table")
    private List<FeaturedItemList> featuredItemList;
}
