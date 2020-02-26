package net.fashiongo.webadmin.controller.request;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;

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
        if (pn != null && pn != 0) {
            params.add("pn=" + pn);
        }
        if (ps != null && ps != 0) {
            params.add("ps=" + ps);
        }
        if (unit != null && unit != 0) {
            params.add("unit=" + unit);
        }
        if (df != null && !df.isEmpty()) {
            params.add("df=" + df);
        }
        if (dt != null && !dt.isEmpty()) {
            params.add("dt=" + dt);
        }
        if (so != null && !so.isEmpty()) {
            params.add("so=" + so);
        }
        if (sq != null && !sq.isEmpty()) {
            params.add("sq=" + sq);
        }
        if (vendorStatus != null && vendorStatus != 0) {
            params.add("vendorStatus=" + vendorStatus);
        }
        if (vendorCategory != null && vendorCategory != 0) {
            params.add("vendorCategory=" + vendorCategory);
        }
        if (vendorType != null && !vendorType.isEmpty()) {
            params.add("vendorType=" + vendorType);
        }
        if (location != null && !location.isEmpty()) {
            params.add("location=" + location);
        }
        if (state != null && !state.isEmpty()) {
            params.add("state=" + state);
        }
        if (assignedUser != null && !assignedUser.isEmpty()) {
            params.add("assignedUser=" + assignedUser);
        }
        if (contractTypeId != null && contractTypeId != 0) {
            params.add("contractTypeId=" + contractTypeId);
        }
        if (orderBy != null && !orderBy.isEmpty()) {
            params.add("orderBy=" + orderBy);
        }

        return params;
    }

}
