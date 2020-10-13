package net.fashiongo.webadmin.data.model.vendor.response.mapper;

import net.fashiongo.webadmin.data.model.vendor.response.VendorContentResponse;
import net.fashiongo.webadmin.model.primary.VendorContent;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VendorContentResponseMapper {

    public static VendorContentResponse create(VendorContent content) {
        return VendorContentResponse.builder()
                .vendorContentId(content.getVendorContentId())
                .wholeSalerId(content.getWholeSalerId())
                .targetTypeId(content.getTargetTypeId())
                .title(content.getTitle())
                .statusId(content.getStatusId())
                .requestedOn(content.getRequestedOn())
                .requestedBy(content.getRequestedBy())
                .approvedOn(content.getApprovedOn())
                .approvedBy(content.getApprovedBy())
                .rejectedOn(content.getRejectedOn())
                .rejectedBy(content.getRejectedBy())
                .rejectedReason(content.getRejectedReason())
                .deletedOn(content.getDeletedOn())
                .deletedBy(content.getDeletedBy())
                .isDeleted(content.getIsDeleted())
                .isActive(content.getIsActive())
                .createdOn(content.getCreatedOn())
                .createdBy(content.getCreatedBy())
                .modifiedOn(content.getModifiedOn())
                .modifiedBy(content.getModifiedBy())
                .vendor(VendorContentVendorResponseMapper.create(content.getVendor()))
                .vendorContentFiles(VendorContentFileResponseMapper.create(content.getVendorContentFiles()))
                .build();
    }

    public static List<VendorContentResponse> create(List<VendorContent> contents) {
        if (CollectionUtils.isEmpty(contents)) {
            return Collections.emptyList();
        }

        return contents.stream().map(VendorContentResponseMapper::create).collect(Collectors.toList());
    }
}
