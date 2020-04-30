package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class GetConsolidationParameter {

    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @JsonProperty("periodtype")
    @ApiModelProperty(required = false, example = "6")
    private Integer periodType;

    @JsonProperty("pagenum")
    @ApiModelProperty(required = false, example = "1")
    private Integer pageNum;

    @JsonProperty("pagesize")
    private Integer pageSize;

    @JsonProperty("dtfrom")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dtFrom;

    @JsonProperty("dtto")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dtTo;

    @JsonProperty("datecolumn")
    private String dateColumn;

    @JsonProperty("bshipped")
    private Integer bshipped;

    @JsonProperty("paymentStatus")
    private Integer paymentStatus;

    @JsonProperty("wn")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String wn;

    @JsonProperty("rn")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String rn;

    @JsonProperty("pn")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String pn;  // po number

    @JsonProperty("cn")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String cn;  // consolidation id

    @JsonProperty("tn")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String tn;

    @JsonProperty("orderby")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String orderBy;

}
