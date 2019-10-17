package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.model.photostudio.*;
import net.fashiongo.webadmin.dao.photostudio.PhotoCalendarRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Repository
public class PhotoCalendarRepositoryCustomImpl implements PhotoCalendarRepositoryCustom {

	@PersistenceContext(unitName = "photostudioEntityManager")
	private EntityManager photostudioEntityManager;

	@Override
	@Transactional(transactionManager = "photostudioTransactionManager")
	public List<PhotoCalendarEntity> getPhotoCalendarWithJoinData(LocalDateTime fromDate, LocalDateTime toDate) {
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

	@Override
	public PhotoCalendarEntity findBeforeBusinessDayFromTheDate(int businessDay, LocalDateTime theDate) {
		QPhotoCalendarEntity calendar = QPhotoCalendarEntity.photoCalendarEntity;

		return new JPAQuery<>(photostudioEntityManager)
				.select(calendar)
				.from(calendar)
				.where(calendar.available.isTrue()
						.and(calendar.isHoliday.isFalse()
								.and(calendar.theDate.loe(theDate))))
				.orderBy(calendar.theDate.desc())
				.limit(1)
				.offset(businessDay - 1)
				.fetchOne();
	}

	@Override
	public List<LocalDateTime> getBusinessDayFromTargetDate(LocalDateTime targetDate, int plusMinus) {
		LocalDateTime date = LocalDateTime.of(targetDate.toLocalDate(), LocalTime.MIN);
		QPhotoCalendarEntity qCalendar = QPhotoCalendarEntity.photoCalendarEntity;

		JPAQuery<LocalDateTime> underQuery = new JPAQuery<>(photostudioEntityManager)
				.select(qCalendar.theDate).distinct()
				.from(qCalendar)
				.where(
						qCalendar.theDate.loe(date)
								.and(qCalendar.isHoliday.isFalse())
				)
				.orderBy(qCalendar.theDate.desc())
				.offset(0)
				.limit(plusMinus);

		JPAQuery<LocalDateTime> upperQuery = new JPAQuery<>(photostudioEntityManager)
				.select(qCalendar.theDate).distinct()
				.from(qCalendar)
				.where(
						qCalendar.theDate.gt(date)
								.and(qCalendar.isHoliday.isFalse())
				)
				.orderBy(qCalendar.theDate.asc())
				.offset(0)
				.limit(plusMinus);

		List<LocalDateTime> result = underQuery.fetch();
		result.addAll(upperQuery.fetch());
		Collections.sort(result);

		return result;
	}
}
