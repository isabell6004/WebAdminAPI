package net.fashiongo.webadmin.model.pojo.payment.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Getter;
import lombok.Setter;
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
    @SQLInjectionSafe
    private String fromDate;

    @JsonProperty("todate")
    @SQLInjectionSafe
    private String toDate;

    @JsonProperty("payoutstatus")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String payoutStatus;

    @JsonProperty("payoutschedule")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String payoutSchedule;

    @JsonProperty("orderby")
    @SQLInjectionSafe
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
