package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VendorBannerSummaryResponse {
    private Long id;
    private Long vendorId;
    private Long bannerTypeId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime requestDate;
    private String requestBy;
    private Boolean isApproved;
    private String rejectReason;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime decidedDate;
    private String decidedBy;
    private Boolean isActive;
    private String originalFileName;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletedOn;
    private String deletedBy;
}
