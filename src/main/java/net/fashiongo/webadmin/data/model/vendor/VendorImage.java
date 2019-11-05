package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VendorImage {
    @JsonProperty(value = "VendorImageTypeID")
    private Integer vendorImageTypeID;

    @JsonProperty(value = "OriginalFileName")
    private String originalFileName;
}
