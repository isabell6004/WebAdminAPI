package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class CouponNotificationCommonInput {

    private CouponNotificationInput notification;
    private MultipartFile targetFile;
    private MultipartFile imageFile;
}
