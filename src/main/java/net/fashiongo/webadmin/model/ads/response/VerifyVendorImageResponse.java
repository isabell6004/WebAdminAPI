package net.fashiongo.webadmin.model.ads.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

@Getter
public class VerifyVendorImageResponse {

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer vendorId;

    private Integer vendorImageType;

    @JsonProperty("isApproved")
    private boolean approved;

    @JsonProperty("isActive")
    private boolean active;

    private VerifyVendorImageResponse() {
    }

    public static VerifyVendorImageResponse of(Integer vendorId, Integer vendorImageType, boolean approved) {

        VerifyVendorImageResponse response = new VerifyVendorImageResponse();

        response.vendorId = vendorId;
        response.vendorImageType = vendorImageType;
        response.approved = approved;
        response.active = Boolean.TRUE;

        return response;
    }

    public static VerifyVendorImageResponse unapproved(Integer vendorId, Integer vendorImageType) {

        VerifyVendorImageResponse response = new VerifyVendorImageResponse();

        response.vendorId = vendorId;
        response.vendorImageType = vendorImageType;
        response.approved = Boolean.FALSE;
        response.active = Boolean.FALSE;

        return response;
    }
}
