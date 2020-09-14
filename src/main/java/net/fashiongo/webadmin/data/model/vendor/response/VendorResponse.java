package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class VendorResponse {
    private Long vendorId;
    private String vendorGuid;
    private String vendorName;
    private String dbaName;
    private String dirName;
    private String description;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startingDate;
    private String website;
    private String businessCategoryInfo;
    private Integer vendorTypeCode;
    private Integer establishedYear;
    private String contactPerson;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedOn;
    private String modifiedBy;

    private VendorSettingSummaryResponse setting;
    private List<VendorBannerSummaryResponse> banners;
    private List<VendorAddressSummaryResponse> addresses;

    public VendorResponse() {
    }

    @Builder
    public VendorResponse(Long vendorId, String vendorGuid, String vendorName, String dbaName, String dirName, String description, String firstName, String lastName, String email, LocalDateTime startingDate, String website, String businessCategoryInfo, Integer vendorTypeCode, Integer establishedYear, String contactPerson, LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy, VendorSettingSummaryResponse setting, List<VendorBannerSummaryResponse> banners, List<VendorAddressSummaryResponse> addresses) {
        this.vendorId = vendorId;
        this.vendorGuid = vendorGuid;
        this.vendorName = vendorName;
        this.dbaName = dbaName;
        this.dirName = dirName;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.startingDate = startingDate;
        this.website = website;
        this.businessCategoryInfo = businessCategoryInfo;
        this.vendorTypeCode = vendorTypeCode;
        this.establishedYear = establishedYear;
        this.contactPerson = contactPerson;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.setting = setting;
        this.banners = banners;
        this.addresses = addresses;
    }
}
