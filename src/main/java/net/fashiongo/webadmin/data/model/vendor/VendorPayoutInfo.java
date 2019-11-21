package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VendorPayoutInfo {
    @JsonProperty(value = "PayoutSchedule")
    private Integer payoutSchedule;

    @JsonProperty(value = "MaxPayoutPerDay")
    private Integer maxPayoutPerDay;

    @JsonProperty(value = "WeekDay")
    private Integer weekDay;

    @JsonProperty(value = "DayofMonth")
    private Integer dayOfMonth;

    @JsonProperty(value = "VerifiedOn")
    private LocalDateTime verifiedOn;
}
