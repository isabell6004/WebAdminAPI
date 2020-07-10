package net.fashiongo.webadmin.data.model.statistics.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class GetAbandonedCartResponse {

    private Long campaignId;
    private String scheduleId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate sentDate;
    private Integer sentCount;
    private Integer bounceCount;
    private Double bounceRate;
    private Integer openCount;
    private Double openRate;
    private Integer clickCount;
    private Double clickRate;
    private Integer conversionCount;
    private Double conversionRate;

    public GetAbandonedCartResponse() {
    }

    @Builder
    public GetAbandonedCartResponse(Long campaignId, String scheduleId, String sentDate, Integer sentCount,
                                    Integer bounceCount, Double bounceRate, Integer openCount, Double openRate,
                                    Integer clickCount, Double clickRate, Integer conversionCount, Double conversionRate) {
        this.campaignId = campaignId;
        this.scheduleId = scheduleId;
        this.sentDate = LocalDate.parse(sentDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));;
        this.sentCount = sentCount;
        this.bounceCount = bounceCount;
        this.bounceRate = bounceRate;
        this.openCount = openCount;
        this.openRate = openRate;
        this.clickCount = clickCount;
        this.clickRate = clickRate;
        this.conversionCount = conversionCount;
        this.conversionRate = conversionRate;
    }
}
