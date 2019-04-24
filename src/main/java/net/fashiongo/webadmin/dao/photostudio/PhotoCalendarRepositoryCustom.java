package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoCalendarEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoCalendarRepositoryCustom {
 	List<PhotoCalendarEntity> getPhotoCalendarWithJoinDate(LocalDateTime fromDate, LocalDateTime toDate);
}
