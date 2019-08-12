package net.fashiongo.webadmin.data.model.ad.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.data.entity.primary.AdPageEntity;
import net.fashiongo.webadmin.data.model.ad.BidAdPage;

import java.util.List;

@Getter
@Setter
public class GetBidAdPagesResponse {
    @JsonProperty("Table")
    private List<BidAdPage> bidAdPage;
}
