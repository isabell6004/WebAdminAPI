package net.fashiongo.webadmin.service;


import net.fashiongo.webadmin.data.model.display.response.DisplayCalendarResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplayLocationResponse;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;

import java.time.LocalDateTime;
import java.util.List;

public interface DisplayService {
    CollectionObject<DisplayLocationResponse> getDisplayLocations();
    CollectionObject<DisplayCalendarResponse> getDisplayCalendar(LocalDateTime startDate, LocalDateTime endDate);
}
