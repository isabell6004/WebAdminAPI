package net.fashiongo.webadmin.model.primary.coupon.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public abstract class CouponCommonInput  {

    @Length(max = 100)
    @NotBlank
    private String couponName;
    @NotNull
    private Integer couponType;
    @NotNull
    private Integer saleType;
    @NotNull
    private Integer discountBaseType;
    @Length(max = 500)
    private String description;
    @Length(max = 500)
    private String discountDescription;
    @Length(max = 500)
    private String useDescription;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isCodeUsed;
    private Boolean isNotified;
    @JsonProperty(value = "maxCouponCount", required = false, defaultValue = "9999999")
    private Integer maxCouponCount;
    @JsonProperty(value = "issuedCouponCount", required = false, defaultValue = "0")
    private Integer issuedCouponCount;
    @JsonProperty(value = "registerType", required = false, defaultValue = "1")
    private Integer registerType;
    private Long couponVendorGroupId;
    private Long couponBuyerGroupId;
    private Integer issueMethod;
    @NotNull
    private LocalDateTime issueStartDate;
    private LocalDateTime issueEndDate;
    private Integer validDurationDays;
    private Boolean isSendEmail;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
