package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetRetailerRatingActiveParameter {
    @JsonProperty(value = "RetailerRatingID")
    private Integer retailerID;

    @JsonProperty(value = "active")
    private Boolean active;
}
