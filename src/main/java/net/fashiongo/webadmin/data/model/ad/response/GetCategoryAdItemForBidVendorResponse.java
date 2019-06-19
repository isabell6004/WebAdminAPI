package net.fashiongo.webadmin.data.model.ad.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.ad.CategoryAdItem;

import java.util.List;

@Getter
@Builder
public class GetCategoryAdItemForBidVendorResponse {
    @JsonProperty("Table")
    private List<CategoryAdItem> categoryAdItem;
}
