package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.buyer.BuyerSearch;

import java.util.List;

@Getter
@Builder
public class BuyerSearchResponse {

    @JsonProperty("Table")
    private List<Total> table;

    @JsonProperty("Table1")
    private List<BuyerSearch> table1;
}
