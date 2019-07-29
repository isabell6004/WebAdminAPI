package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class CouponNotificationInput {

    private Long id;
    private Long couponId;
    @NotNull
    private Integer notificationMethod;
    @Length(max = 200)
    @NotBlank
    private String notificationSubject;
    @Length(max = 200)
    private String notificationPreviewText;
    @Length(max = 200)
    private String notificationImageFileName;
    @Length(max = 200)
    private String notificationTargetFile;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
