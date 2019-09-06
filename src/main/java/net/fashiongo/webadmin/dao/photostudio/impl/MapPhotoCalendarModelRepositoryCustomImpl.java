package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoCalendarModelRepositoryCustom;
import net.fashiongo.webadmin.model.photostudio.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MapPhotoCalendarModelRepositoryCustomImpl implements MapPhotoCalendarModelRepositoryCustom {

	@PersistenceContext(unitName = "photostudioEntityManager")
	private EntityManager photostudioEntityManager;

	@Override
	public List<MapPhotoCalendarModel> findAvailableMapByTheDate(LocalDate theDate) {
		QMapPhotoCalendarModel mapPhotoCalendarModel = QMapPhotoCalendarModel.mapPhotoCalendarModel;
		QPhotoCalendarEntity photoCalendar = QPhotoCalendarEntity.photoCalendarEntity;
		QPhotoBooking photoBooking = QPhotoBooking.photoBooking;
		QPhotoModel photoModel = QPhotoModel.photoModel;
		QPhotoOrderEntity photoOrderEntity = QPhotoOrderEntity.photoOrderEntity;

		JPAQuery<MapPhotoCalendarModel> query = new JPAQuery<>(photostudioEntityManager)
				.select(mapPhotoCalendarModel).distinct()
				.from(mapPhotoCalendarModel)
				.leftJoin(mapPhotoCalendarModel.photoModel, photoModel).fetchJoin()
				.join(mapPhotoCalendarModel.photoCalendarEntity, photoCalendar).fetchJoin()
				.leftJoin(mapPhotoCalendarModel.photoBooking, photoBooking).fetchJoin()
				.leftJoin(photoBooking.photoOrder, photoOrderEntity).fetchJoin()
				.where(photoCalendar.theDate.eq(theDate.atTime(0, 0))
                        .and(photoCalendar.theDate.after(LocalDateTime.now().minusDays(1)))
						.and(photoCalendar.available.isTrue())
						.and(photoCalendar.isHoliday.isFalse())
						.and(mapPhotoCalendarModel.isDelete.isFalse()
								.or(mapPhotoCalendarModel.isDelete.isNull())));

		return query.fetch();
	}

	@Override
	public List<MapPhotoCalendarModel> findAvailableMapAfterNowByModelIdsAndNotTheDate(List<Integer> modelIds, LocalDate theDate, boolean isFullModelShot) {
		if(modelIds.size() == 0 && isFullModelShot) return new ArrayList<>();

		QMapPhotoCalendarModel mapPhotoCalendarModel = QMapPhotoCalendarModel.mapPhotoCalendarModel;
		QPhotoCalendarEntity photoCalendar = QPhotoCalendarEntity.photoCalendarEntity;
		QPhotoBooking photoBooking = QPhotoBooking.photoBooking;
		QPhotoOrderEntity photoOrderEntity = QPhotoOrderEntity.photoOrderEntity;

		BooleanExpression booleanExpression = photoCalendar.available.isTrue()
				.and(photoCalendar.isHoliday.isFalse())
				.and(photoCalendar.theDate.after(LocalDateTime.now()))
				.and(mapPhotoCalendarModel.isDelete.isFalse()
						.or(mapPhotoCalendarModel.isDelete.isNull()))
				.and(mapPhotoCalendarModel.photoCalendarEntity.theDate.ne(theDate.atTime(0, 0)));

		if (!isFullModelShot) {
			booleanExpression = booleanExpression.and(mapPhotoCalendarModel.modelID.isNull());
		} else {
			booleanExpression = booleanExpression.and(mapPhotoCalendarModel.modelID.in(modelIds));
		}

		JPAQuery<MapPhotoCalendarModel> query = new JPAQuery<>(photostudioEntityManager)
				.select(mapPhotoCalendarModel).distinct()
				.from(mapPhotoCalendarModel)
				.join(mapPhotoCalendarModel.photoCalendarEntity, photoCalendar).fetchJoin()
				.leftJoin(mapPhotoCalendarModel.photoBooking, photoBooking).fetchJoin()
				.leftJoin(photoBooking.photoOrder, photoOrderEntity).fetchJoin()
				.where(booleanExpression);

		return query.fetch();
	}

	@Override
	public List<MapPhotoCalendarModel> findByModelIdWithBooking(int modelId) {
		QMapPhotoCalendarModel mapPhotoCalendarModel = QMapPhotoCalendarModel.mapPhotoCalendarModel;
		QPhotoBooking photoBooking = QPhotoBooking.photoBooking;
		QPhotoOrderEntity photoOrder = QPhotoOrderEntity.photoOrderEntity;

		JPAQuery<MapPhotoCalendarModel> query = new JPAQuery<>(photostudioEntityManager)
				.select(mapPhotoCalendarModel).distinct()
				.from(mapPhotoCalendarModel)
				.join(mapPhotoCalendarModel.photoBooking, photoBooking).fetchJoin()
				.join(photoBooking.photoOrder, photoOrder).fetchJoin()
				.where(mapPhotoCalendarModel.modelID.eq(modelId)
						.and(photoBooking.statusID.eq(0)));

		return query.fetch();
	}

	@Override
	public List<MapPhotoCalendarModel> findWithModelAndBookingByCalendarId(int calendarId) {
		QMapPhotoCalendarModel map = QMapPhotoCalendarModel.mapPhotoCalendarModel;
		QPhotoBooking booking = QPhotoBooking.photoBooking;
		QPhotoModel model = QPhotoModel.photoModel;
		QPhotoOrderEntity photoOrder = QPhotoOrderEntity.photoOrderEntity;

		JPAQuery<MapPhotoCalendarModel> query = new JPAQuery<>(photostudioEntityManager)
				.select(map).distinct()
				.from(map)
				.leftJoin(map.photoModel, model).fetchJoin()
				.leftJoin(map.photoBooking, booking).fetchJoin()
				.leftJoin(booking.photoOrder, photoOrder).fetchJoin()
				.where(map.calendarID.eq(calendarId)
						.and(map.isDelete.isNull()
								.or(map.isDelete.isFalse())));

		return query.fetch();
	}
}
