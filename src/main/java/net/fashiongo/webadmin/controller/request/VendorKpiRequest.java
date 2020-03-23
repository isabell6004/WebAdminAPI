package net.fashiongo.webadmin.controller.request;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VendorKpiRequest {

    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @NotNull
    private Integer pn;

    @NotNull
    private Integer ps;

    private Integer unit;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String df;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String dt;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String so;

    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String sq;

    private Integer vendorStatus;

    private Integer vendorCategory;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String vendorType;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String location;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String state;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String assignedUser;

    private Integer contractTypeId;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String orderBy;

    public List<String> getParams() {
        List<String> params = new ArrayList<>();

        addIntegerParams(params, pn, "pn=");
        addIntegerParams(params, ps, "ps=");
        addIntegerParams(params, unit, "unit=");
        addStringParams(params, df, "df=");
        addStringParams(params, dt, "dt=");
        addStringParams(params, so, "so=");
        addStringParams(params, sq, "sq=");
        addIntegerParams(params, vendorStatus, "vendorStatus=");
        addIntegerParams(params, vendorCategory, "vendorCategory=");
        addStringParams(params, vendorType, "vendorType=");
        addStringParams(params, location, "location=");
        addStringParams(params, state, "state=");
        addStringParams(params, assignedUser, "assignedUser=");
        addIntegerParams(params, contractTypeId, "contractTypeId=");
        addStringParams(params, orderBy, "orderBy=");

        return params;
    }

    public void addIntegerParams(List<String> params, Integer integerParam, String name) {
        if (integerParam != null && integerParam.intValue() != 0) {
            params.add(name + integerParam);
        }
    }

    public void addStringParams(List<String> params, String stringParam, String name) {
        if (!StringUtils.isEmpty(stringParam)) {
            params.add(name + stringParam.replace("'", "''"));
        }
    }

}
