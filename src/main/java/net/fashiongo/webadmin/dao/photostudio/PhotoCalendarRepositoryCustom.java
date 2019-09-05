package net.fashiongo.webadmin.dao.photostudio;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.model.photostudio.PhotoCalendarEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public interface PhotoCalendarRepositoryCustom {
 	List<PhotoCalendarEntity> getPhotoCalendarWithJoinDate(LocalDateTime fromDate, LocalDateTime toDate);

	PhotoCalendarEntity findBeforeBusinessDayFromTheDate(int businessDay, LocalDateTime theDate);

	List<LocalDateTime> getBusinessDayFromTargetDate(LocalDateTime targetDate, int plusMinus);
}
