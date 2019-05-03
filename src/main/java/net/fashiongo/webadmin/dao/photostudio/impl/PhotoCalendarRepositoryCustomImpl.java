package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.model.photostudio.*;
import net.fashiongo.webadmin.dao.photostudio.PhotoCalendarRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PhotoCalendarRepositoryCustomImpl implements PhotoCalendarRepositoryCustom {

	@PersistenceContext(unitName = "photostudioEntityManager")
	private EntityManager photostudioEntityManager;

	@Override
	@Transactional(transactionManager = "photostudioTransactionManager")
	public List<PhotoCalendarEntity> getPhotoCalendarWithJoinDate(LocalDateTime fromDate, LocalDateTime toDate) {
		JPAQuery<PhotoCalendarEntity> query = new JPAQuery<>(photostudioEntityManager)
				.select(QPhotoCalendarEntity.photoCalendarEntity).distinct()
				.from(QPhotoCalendarEntity.photoCalendarEntity)
				.leftJoin(QPhotoCalendarEntity.photoCalendarEntity.mapPhotoCalendarModel, QMapPhotoCalendarModel.mapPhotoCalendarModel).fetchJoin()
				.leftJoin(QMapPhotoCalendarModel.mapPhotoCalendarModel.photoModel, QPhotoModel.photoModel).fetchJoin()
				.leftJoin(QMapPhotoCalendarModel.mapPhotoCalendarModel.photoBooking, QPhotoBooking.photoBooking).fetchJoin()
				.leftJoin(QPhotoBooking.photoBooking.photoOrder, QPhotoOrderEntity.photoOrderEntity).fetchJoin()
				.where(
						QPhotoCalendarEntity.photoCalendarEntity.theDate.goe(fromDate)
								.and(QPhotoCalendarEntity.photoCalendarEntity.theDate.loe(toDate))
				);

		return query.fetch();
	}
}
