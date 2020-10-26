package net.fashiongo.webadmin.data.model.collection.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CollectionVendorLatestPromotionResponse {

    @JsonProperty("vendorId")
    private int vendorId;

    @JsonProperty("promotionName")
    private String promotionName;

    @JsonProperty("toDate")
    private LocalDateTime toDate;

    // compare with server local time
    @JsonProperty("isExpired")
    private boolean isExpired;
}
