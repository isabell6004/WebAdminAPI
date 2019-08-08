package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAUpdateClause;
import net.fashiongo.webadmin.data.entity.primary.QCouponNotificationEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class CouponNotificationEntityRepositoryCustomImpl implements CouponNotificationEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(value = "primaryTransactionManager")
	public long updateTargetFileName(int couponNotificationId, String fileName) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		QCouponNotificationEntity PATH = QCouponNotificationEntity.couponNotificationEntity;
		JPAUpdateClause updateClause = new JPAUpdateClause(entityManager, PATH);

		return updateClause.set(PATH.modifiedOn,now)
				.set(PATH.notificationTargetFile,fileName)
				.where(PATH.couponNotificationId.eq(couponNotificationId))
				.execute();
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public long updateImageFileName(int couponNotificationId, String fileName) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		QCouponNotificationEntity PATH = QCouponNotificationEntity.couponNotificationEntity;
		JPAUpdateClause updateClause = new JPAUpdateClause(entityManager, PATH);

		return updateClause.set(PATH.modifiedOn,now)
				.set(PATH.notificationImageFileName,fileName)
				.where(PATH.couponNotificationId.eq(couponNotificationId))
				.execute();
	}
}
