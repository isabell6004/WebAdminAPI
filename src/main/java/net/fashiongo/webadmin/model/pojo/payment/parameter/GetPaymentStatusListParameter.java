package net.fashiongo.webadmin.model.pojo.payment.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class GetPaymentStatusListParameter {

    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s !&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @ApiModelProperty(required = false, example = "1")
    @JsonProperty("PageNum")
    private Integer pageNum;

    @ApiModelProperty(required = false, example = "20")
    @JsonProperty("PageSize")
    private Integer pageSize;

    @ApiModelProperty(required = false, example = "2858")
    @JsonProperty("WholeSalerID")
    private Integer wholeSalerID;

    @ApiModelProperty(required = false, example = "2")
    @JsonProperty("PaymentStatusID")
    private Integer paymentStatusID;

    @ApiModelProperty(required = false, example = "2017-01-01 00:00:00.000")
    @JsonProperty("FromDate")
    @SQLInjectionSafe
    private String fromDate;

    @ApiModelProperty(required = false, example = "2017-12-31 00:00:00.000")
    @JsonProperty("ToDate")
    @SQLInjectionSafe
    private String toDate;

    @ApiModelProperty(required = false, example = "test")
    @JsonProperty("PONumber")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String poNumber;

    @ApiModelProperty(required = false, example = "2")
    @JsonProperty("ConsolidationID")
    private Integer consolidationID;

    @ApiModelProperty(required = false, example = "test")
    @JsonProperty("BuyerName")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String buyerName;

    @ApiModelProperty(required = false, example = "0")
    @JsonProperty("TransactionType")
    private Integer transactionType;

    @ApiModelProperty(required = false, example = "1")
    @JsonProperty("searchSuccess")
    private Integer searchSuccess;

    @ApiModelProperty(required = false, example = "1")
    @JsonProperty("OrderBy")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String orderBy;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    public LocalDateTime getFromDate() {
        if (StringUtils.isNotEmpty(fromDate)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
            return LocalDateTime.parse(fromDate, formatter);
        }
        return null;
    }

    public LocalDateTime getToDate() {
        if (StringUtils.isNotEmpty(toDate)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
            return LocalDateTime.parse(toDate, formatter);
        }
        return null;
    }

    public String getPoNumber() {
        return StringUtils.isEmpty(poNumber) ? null : poNumber;
    }

    public String getBuyerName() {
        return StringUtils.isEmpty(buyerName) ? null : buyerName;
    }

    public String getOrderBy() {
        return StringUtils.isEmpty(orderBy) ? null : orderBy;
    }

}
