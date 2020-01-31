package net.fashiongo.webadmin.model.pojo.payment.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class GetPayoutHistoryParameter {

    private static final String ALLOW_ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]+$";
    private static final String ALLOW_ALPHANUMERIC_PATTERN_MESSAGE = "Alphanumeric characters are only allowed";
    private static final String ALLOW_ALPHABET_PATTERN = "^[A-Za-z]+$";
    private static final String ALLOW_ALPHABET_PATTERN_MESSAGE = "Alphabet characters are only allowed";
    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s !&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @JsonProperty("pagenum")
    @NotNull
    private Integer pageNum;

    @JsonProperty("pagesize")
    @NotNull
    private Integer pageSize;

    @JsonProperty("wholesalerid")
    private Integer wholesalerId;

    @JsonProperty("fromdate")
    @SQLInjectionSafeWithKeywordsFilter
    private String fromDate;

    @JsonProperty("todate")
    @SQLInjectionSafeWithKeywordsFilter
    private String toDate;

    @JsonProperty("payoutstatus")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String payoutStatus;

    @JsonProperty("payoutschedule")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String payoutSchedule;

    @JsonProperty("orderby")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String orderBy;

    public String getFromDate() {
        return StringUtils.isEmpty(fromDate) ? null : fromDate;
    }

    public String getToDate() {
        return StringUtils.isEmpty(toDate) ? null : toDate;
    }

    public String getPayoutStatus() {
        return StringUtils.isEmpty(payoutStatus) ? null : payoutStatus;
    }

    public String getPayoutSchedule() {
        if (StringUtils.isEmpty(payoutSchedule)) {
            return null;
        }
        return payoutSchedule.equalsIgnoreCase("manually") ? "Manual" : payoutSchedule;
    }

    public String getOrderBy() {
        return StringUtils.isEmpty(orderBy) ? null : orderBy;
    }

}
