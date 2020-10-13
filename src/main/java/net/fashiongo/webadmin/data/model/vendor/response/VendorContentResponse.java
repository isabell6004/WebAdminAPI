package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class VendorContentResponse {
    private Integer vendorContentId;
    private Integer wholeSalerId;
    private Integer targetTypeId;
    private String title;
    private Integer statusId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime requestedOn;
    private String requestedBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime approvedOn;
    private String approvedBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime rejectedOn;
    private String rejectedBy;
    private String rejectedReason;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletedOn;
    private String deletedBy;
    private Boolean isDeleted;
    private Boolean isActive;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedOn;
    private String modifiedBy;

    private VendorContentVendorResponse vendor;
    private List<VendorContentFileResponse> vendorContentFiles = new ArrayList<>();

    public VendorContentResponse() {
    }

    @Builder
    public VendorContentResponse(Integer vendorContentId, Integer wholeSalerId, Integer targetTypeId, String title,
                                 Integer statusId, LocalDateTime requestedOn, String requestedBy, LocalDateTime approvedOn,
                                 String approvedBy, LocalDateTime rejectedOn, String rejectedBy, String rejectedReason,
                                 LocalDateTime deletedOn, String deletedBy, Boolean isDeleted, Boolean isActive,
                                 LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy,
                                 VendorContentVendorResponse vendor, List<VendorContentFileResponse> vendorContentFiles) {
        this.vendorContentId = vendorContentId;
        this.wholeSalerId = wholeSalerId;
        this.targetTypeId = targetTypeId;
        this.title = title;
        this.statusId = statusId;
        this.requestedOn = requestedOn;
        this.requestedBy = requestedBy;
        this.approvedOn = approvedOn;
        this.approvedBy = approvedBy;
        this.rejectedOn = rejectedOn;
        this.rejectedBy = rejectedBy;
        this.rejectedReason = rejectedReason;
        this.deletedOn = deletedOn;
        this.deletedBy = deletedBy;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.vendor = vendor;
        this.vendorContentFiles = vendorContentFiles;
    }
}
