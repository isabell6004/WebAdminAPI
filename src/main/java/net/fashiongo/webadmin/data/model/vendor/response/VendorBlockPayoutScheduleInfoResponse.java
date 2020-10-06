package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VendorBlockPayoutScheduleInfoResponse {
    private Long id;
    private Long vendorId;
    private Integer payoutSchedule;
    private Integer weekday;
    private Integer dayOfMonth;
    private LocalDateTime createdOn;
    private String createdBy;

    public VendorBlockPayoutScheduleInfoResponse() {}
}
