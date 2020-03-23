package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Getter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class GetVendorsGeneralInfoParameter {

    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @NotNull
    @JsonProperty(value = "pn")
    private Integer pn;

    @NotNull
    @JsonProperty(value = "ps")
    private Integer ps;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    @JsonProperty(value = "so")
    private String so;

    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    @JsonProperty(value = "sq")
    private String sq;

    @JsonProperty(value = "vendorStatus")
    private Integer vendorStatus;

    @JsonProperty(value = "vendorCategory")
    private Integer vendorCategory;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    @JsonProperty(value = "vendorType")
    private String vendorType;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    @JsonProperty(value = "location")
    private String location;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    @JsonProperty(value = "state")
    private String state;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    @JsonProperty(value = "assignedUser")
    private String assignedUser;

    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    @JsonProperty(value = "orderBy")
    private String orderBy;

}
