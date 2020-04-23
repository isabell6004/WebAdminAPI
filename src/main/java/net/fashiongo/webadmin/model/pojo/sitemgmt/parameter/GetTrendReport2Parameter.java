package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nayeon Kim
 */
public class GetTrendReport2Parameter {

    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @ApiModelProperty(required = false, example = "1")
    public Integer pagenum;

    @ApiModelProperty(required = false, example = "10")
    public Integer pagesize;

    @ApiModelProperty(required = false, example = "")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    public String searchtxt;

    @ApiModelProperty(required = false, example = "9/1/2018 00:00:00")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    public String fromdate;

    @ApiModelProperty(required = false, example = "9/30/2018 23:59:59")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    public String todate;

    @ApiModelProperty(required = false, example = "true")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    public String active;

    @ApiModelProperty(required = false, example = "DateFrom")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    public String orderby;

    @ApiModelProperty(required = false, example = "Desc")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    public String orderbygubn;

    @ApiModelProperty(required = false, example = "1")
    @JsonProperty("CuratedType")
    public Integer curatedType;

    public Integer getPagenum() {
        return pagenum != null ? pagenum : 1;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    public Integer getPagesize() {
        return pagesize != null ? pagesize : 10;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getSearchtxt() {
        return StringUtils.isEmpty(searchtxt) ? null : searchtxt.replace("'", "''");
    }

    public void setSearchtxt(String searchtxt) {
        this.searchtxt = searchtxt;
    }

    public LocalDateTime getFromdate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
        return StringUtils.isEmpty(this.fromdate) ? null : LocalDateTime.parse(this.fromdate, formatter);
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public LocalDateTime getTodate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
        return StringUtils.isEmpty(this.todate) ? null : LocalDateTime.parse(this.todate, formatter);
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public Boolean getActive() {
        return StringUtils.isEmpty(this.active) ? null : Boolean.parseBoolean(this.active);
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getOrderby() {
        return StringUtils.isEmpty(orderby) ? null : orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getOrderbygubn() {
        return StringUtils.isEmpty(orderbygubn) ? null : orderbygubn;
    }

    public void setOrderbygubn(String orderbygubn) {
        this.orderbygubn = orderbygubn;
    }

    public Integer getCuratedType() {
        return curatedType;
    }

    public void setCuratedType(Integer curatedType) {
        this.curatedType = curatedType;
    }
}






