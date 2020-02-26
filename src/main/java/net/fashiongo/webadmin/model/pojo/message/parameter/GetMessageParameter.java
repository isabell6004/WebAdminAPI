package net.fashiongo.webadmin.model.pojo.message.parameter;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class GetMessageParameter {

    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @ApiModelProperty(required = true, example = "1")
    private Integer pagenum;

    @ApiModelProperty(required = true, example = "30")
    private Integer pagesize;

    private Integer parent;

    private Integer recipienttypeid;

    @ApiModelProperty(required = true, example = "1")
    private Integer sendertypeid;

    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String sender;

    private Integer topic;

    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String subject;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String period;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String fromdate;

    public String getFromdate() {
        return StringUtils.isEmpty(fromdate) ? null : fromdate;
    }

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String todate;

    public String getTodate() {
        return StringUtils.isEmpty(todate) ? null : todate;
    }

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String status;

}
