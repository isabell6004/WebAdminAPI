package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public abstract class CouponCodeCommonInput {

    @Length(max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]{0,20}")
    private String couponCode;
    @NotNull
    private Integer generateType;
    private Long couponId;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
