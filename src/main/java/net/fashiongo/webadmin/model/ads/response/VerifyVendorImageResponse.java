package net.fashiongo.webadmin.model.ads.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

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

    public static VerifyVendorImageResponse of(Integer vendorId, Integer vendorImageType, boolean approved, boolean active) {

        VerifyVendorImageResponse response = new VerifyVendorImageResponse();

        response.vendorId = vendorId;
        response.vendorImageType = vendorImageType;
        response.approved = approved;
        response.active = active;

        return response;
    }

    public static VerifyVendorImageResponse from(List<VendorImageRequestEntity> vendorImageRequestEntities) {

        if (CollectionUtils.isEmpty(vendorImageRequestEntities))
            throw new IllegalArgumentException("argument is null");

        VerifyVendorImageResponse response = new VerifyVendorImageResponse();
        VendorImageRequestEntity vendorImageRequestEntity = vendorImageRequestEntities.stream()
                .max(Comparator.comparing(VendorImageRequestEntity::getImageRequestId))
                .get();

        response.vendorId = vendorImageRequestEntity.getWholesalerId();
        response.vendorImageType = vendorImageRequestEntity.getVendorImageTypeId();
        response.approved = vendorImageRequestEntity.getIsApproved();
        response.active = vendorImageRequestEntity.isActive();

        return response;
    }
}
