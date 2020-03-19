package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class VendorBannerResponse {
    private Long id;
    private Long vendorId;
    private String companyName;
    private Long bannerTypeId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime requestDate;
    private String requestBy;
    private Boolean isApproved;
    private String rejectReason;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime decidedDate;
    private String decidedBy;
    private Boolean isActive;
    private String originalFileName;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime deletedOn;
    private String deletedBy;
    private String dirname;

    public VendorBannerResponse() {
    }

    @Builder
    public VendorBannerResponse(Long id, Long vendorId, String companyName, Long bannerTypeId, String requestDate, String requestBy, Boolean isApproved, String rejectReason, String decidedDate, String decidedBy, Boolean isActive, String originalFileName, String deletedOn, String deletedBy, String dirname) {
        this.id = id;
        this.vendorId = vendorId;
        this.companyName = companyName;
        this.bannerTypeId = bannerTypeId;
        this.requestDate = requestDate == null ? null : LocalDateTime.parse(requestDate, DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss.SSS"));
        this.requestBy = requestBy;
        this.isApproved = isApproved;
        this.rejectReason = rejectReason;
        this.decidedDate = decidedDate == null ? null : LocalDateTime.parse(decidedDate, DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss.SSS"));
        this.decidedBy = decidedBy;
        this.isActive = isActive;
        this.originalFileName = originalFileName;
        this.deletedOn = deletedOn == null ? null : LocalDateTime.parse(deletedOn, DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss.SSS"));
        this.deletedBy = deletedBy;
        this.dirname = dirname;
    }
}
