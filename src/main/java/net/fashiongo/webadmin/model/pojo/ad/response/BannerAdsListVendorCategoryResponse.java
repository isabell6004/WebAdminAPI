package net.fashiongo.webadmin.model.pojo.ad.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BannerAdsListVendorCategoryResponse {

    @JsonProperty("Table")
    private List<AdVendorCategoryResponse> table;

    private BannerAdsListVendorCategoryResponse() {
    }

    public static BannerAdsListVendorCategoryResponse of(List<AdVendorCategoryResponse> responses) {

        BannerAdsListVendorCategoryResponse response = new BannerAdsListVendorCategoryResponse();

        response.table = responses;

        return response;
    }
}
