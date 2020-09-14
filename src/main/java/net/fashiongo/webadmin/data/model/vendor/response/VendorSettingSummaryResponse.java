package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VendorSettingSummaryResponse {
    private Integer statusCode;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime openDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime closedDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedOn;
    private String modifiedBy;

    public VendorSettingSummaryResponse() {
    }

    @Builder
    public VendorSettingSummaryResponse(Integer statusCode, LocalDateTime openDate, LocalDateTime closedDate, LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy) {
        this.statusCode = statusCode;
        this.openDate = openDate;
        this.closedDate = closedDate;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
    }
}
