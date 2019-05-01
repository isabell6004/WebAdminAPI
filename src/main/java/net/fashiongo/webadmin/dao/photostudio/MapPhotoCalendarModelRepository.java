package net.fashiongo.webadmin.dao.photostudio;

import java.util.List;

import net.fashiongo.webadmin.model.photostudio.MapPhotoCalendarModel;

import org.springframework.data.repository.CrudRepository;

public interface MapPhotoCalendarModelRepository extends CrudRepository<MapPhotoCalendarModel, Integer>, MapPhotoCalendarModelRepositoryCustom {
	List<MapPhotoCalendarModel> findByCalendarID(Integer calendarID);
}
