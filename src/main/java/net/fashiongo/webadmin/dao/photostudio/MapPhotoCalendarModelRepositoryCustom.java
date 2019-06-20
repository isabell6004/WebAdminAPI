package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.MapPhotoCalendarModel;

import java.time.LocalDate;
import java.util.List;

public interface MapPhotoCalendarModelRepositoryCustom {
	List<MapPhotoCalendarModel> findAvailableMapByTheDate(LocalDate theDate);
	List<MapPhotoCalendarModel> findAvailableMapAfterNowByModelIdsAndNotTheDate(List<Integer> modelIds, LocalDate theDate, boolean isFullModelShot);
	List<MapPhotoCalendarModel> findByModelIdWithBooking(int modelId);
	List<MapPhotoCalendarModel> findWithModelAndBookingByCalendarId(int calendarId);
}
