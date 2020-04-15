package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VendorImage {
    @JsonProperty(value = "VendorImageTypeID")
    private Integer vendorImageTypeID;

    @JsonProperty(value = "OriginalFileName")
    private String originalFileName;

    public VendorImage() {
    }

    @Builder
    public VendorImage(Integer vendorImageTypeID, String originalFileName) {
        this.vendorImageTypeID = vendorImageTypeID;
        this.originalFileName = originalFileName;
    }
}
