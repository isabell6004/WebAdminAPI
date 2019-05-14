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

		JPAQuery<MapPhotoCalendarModel> query = new JPAQuery<>(photostudioEntityManager)
				.select(mapPhotoCalendarModel).distinct()
				.from(mapPhotoCalendarModel)
				.join(mapPhotoCalendarModel.photoModel, photoModel).fetchJoin()
				.join(mapPhotoCalendarModel.photoCalendarEntity, photoCalendar).fetchJoin()
				.leftJoin(mapPhotoCalendarModel.photoBooking, photoBooking).fetchJoin()
				.where(photoCalendar.theDate.eq(theDate.atTime(0, 0))
						.and(photoCalendar.theDate.after(LocalDateTime.now()))
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
				.where(booleanExpression);

		return query.fetch();
	}
}
