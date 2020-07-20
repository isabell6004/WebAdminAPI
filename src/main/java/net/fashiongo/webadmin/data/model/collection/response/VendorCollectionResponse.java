package net.fashiongo.webadmin.data.model.collection.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class VendorCollectionResponse {

    private int vendorId;

    private String vendorName;

    @JsonProperty("isAvailable")
    private boolean isAvailable;

    private String bannerImageUrl;
}
