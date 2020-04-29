package net.fashiongo.webadmin.model.pojo.admin.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;

import javax.validation.constraints.Pattern;

public class GetSecurityUserParameter {

    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!@&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @JsonProperty("userid")
    private Integer userID;

    @ApiModelProperty(required = false, example = "Vicky")
    @JsonProperty("username")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String userName;

    @ApiModelProperty(required = false, example = "Call Center")
    @JsonProperty("group")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String group;

    @ApiModelProperty(required = false, example = "S")
    @JsonProperty("role")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String role;

    @ApiModelProperty(required = false, example = "2858")
    @JsonProperty("assignedvendor")
    private Integer vendorID;

    @ApiModelProperty(required = false, example = "2")
    @JsonProperty("dataAccessLevel")
    private Integer dataAccessLevel;

    @JsonProperty("active")
    private Boolean active;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getVendorID() {
        return vendorID;
    }

    public void setVendorID(Integer vendorID) {
        this.vendorID = vendorID;
    }

    public Integer getDataAccessLevel() {
        return dataAccessLevel;
    }

    public void setDataAccessLevel(Integer dataAccessLevel) {
        this.dataAccessLevel = dataAccessLevel;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
