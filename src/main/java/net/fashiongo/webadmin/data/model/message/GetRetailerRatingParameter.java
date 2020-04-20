package net.fashiongo.webadmin.data.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class GetRetailerRatingParameter {

    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s =/:!&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @JsonProperty("wholesalerid")
    private Integer wholesalerId;

    @JsonProperty("retailerid")
    private Integer retailerId;

    @JsonProperty("pagenum")
    private Integer pageNum;

    @JsonProperty("pagesize")
    private Integer pageSize;

    @JsonProperty("from")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String fromDate;

    @JsonProperty("to")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String toDate;

    @JsonProperty("active")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String active;

    @JsonProperty("type")
    private Boolean type;

    @JsonProperty("orderby")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String orderby;

    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String retailerCompanyName;

    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String wholesalerCompanyName;

    private Integer score;

    public Integer getPageNum() {
        return pageNum == null ? 0 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 0 : pageSize;
    }

    public String getFromDate() {
        return StringUtils.isEmpty(fromDate) ? null : fromDate;
    }

    public String getToDate() {
        return StringUtils.isEmpty(toDate) ? null : toDate;
    }

    public Boolean getActive() {
        return StringUtils.isEmpty(active) ? null : active.equals("1");
    }

    public String getOrderby() {
        return StringUtils.isEmpty(orderby) ? null : orderby;
    }

}
