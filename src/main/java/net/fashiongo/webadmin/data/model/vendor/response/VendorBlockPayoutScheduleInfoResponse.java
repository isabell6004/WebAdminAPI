package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
public class VendorBlockPayoutScheduleInfoResponse {
    private Integer payoutSchedule;
    private Integer weekday;
    private Integer dayOfMonth;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;
    private String createdBy;

    public VendorBlockPayoutScheduleInfoResponse() {
    }

    @Builder
    public VendorBlockPayoutScheduleInfoResponse(Integer payoutSchedule, Integer weekday, Integer dayOfMonth, LocalDateTime createdOn, String createdBy) {
        this.payoutSchedule = payoutSchedule;
        this.weekday = weekday;
        this.dayOfMonth = dayOfMonth;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
    }
}
