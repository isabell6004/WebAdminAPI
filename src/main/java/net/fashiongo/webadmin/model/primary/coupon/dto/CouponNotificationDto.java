package net.fashiongo.webadmin.model.primary.coupon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.data.model.entity.coupon.CCouponNotification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class CouponNotificationDto {

    private Long id;
    private Long couponId;
    private Integer notificationMethod;
    private Boolean isSent;
    private LocalDateTime sentDate;
    private String notificationSubject;
    private String notificationPreviewText;
    private String notificationImageFileName;
    private String notificationTargetFile;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
    private String imageRootUrl;

    public static CouponNotificationDto build(CCouponNotification notification, String imageRootUrl) {

        if (notification == null) {
            return null;
        }

        return builder()
                .id(notification.getId())
                .couponId(notification.getCouponId())
                .notificationMethod(notification.getNotificationMethod())
                .isSent(notification.getIsSent())
                .sentDate(notification.getSentDate())
                .notificationSubject(notification.getNotificationSubject())
                .notificationPreviewText(notification.getNotificationPreviewText())
                .notificationImageFileName(notification.getNotificationImageFileName())
                .notificationTargetFile(notification.getNotificationTargetFile())
                .createdOn(notification.getCreatedOn())
                .createdBy(notification.getCreatedBy())
                .modifiedOn(notification.getModifiedOn())
                .modifiedBy(notification.getModifiedBy())
                .imageRootUrl(imageRootUrl)
                .build();
    }

    public static List<CouponNotificationDto> build(List<CCouponNotification> notifications, String imageRootUrl) {

        if (CollectionUtils.isEmpty(notifications)) {
            return Collections.emptyList();
        }

        return notifications.stream()
                .map(n -> CouponNotificationDto.build(n, imageRootUrl))
                .collect(Collectors.toList());
    }
}
