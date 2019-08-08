package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CouponNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponNotificationEntityRepository extends JpaRepository<CouponNotificationEntity,Integer>, CouponNotificationEntityRepositoryCustom {
}
