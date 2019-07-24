package net.fashiongo.webadmin.data.repository.primary;

public interface CouponNotificationEntityRepositoryCustom {

	long updateTargetFileName(int couponNotificationId,String fileName);

	long updateImageFileName(int couponNotificationId,String fileName);
}
