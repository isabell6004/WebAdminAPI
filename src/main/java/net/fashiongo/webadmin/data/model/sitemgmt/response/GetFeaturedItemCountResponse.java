package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.data.model.sitemgmt.FeaturedItemCount;
import net.fashiongo.webadmin.data.model.sitemgmt.FeaturedItemList;

import java.util.List;

@Getter
@Setter
public class GetFeaturedItemCountResponse {
    @JsonProperty("Table")
    private List<FeaturedItemCount> featuredItemCount;

    @JsonProperty("Table1")
    private List<FeaturedItemList> featuredItemList;
}
