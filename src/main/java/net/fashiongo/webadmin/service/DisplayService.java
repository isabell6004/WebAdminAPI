package net.fashiongo.webadmin.service;


import net.fashiongo.webadmin.data.model.display.DisplaySettingParameter;
import net.fashiongo.webadmin.data.model.display.response.DisplayCalendarResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplayCollectionResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplayLocationResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplaySettingResponse;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;

import java.time.LocalDateTime;

public interface DisplayService {
    CollectionObject<DisplayLocationResponse> getDisplayLocations();
    CollectionObject<DisplayCollectionResponse> getDisplayCollections();
    CollectionObject<DisplayCalendarResponse> getDisplayCalendar(LocalDateTime startDate, LocalDateTime endDate);
    SingleObject<DisplaySettingResponse> getDisplaySetting(int displaySettingId);
    SingleObject<Integer> createDisplaySetting(DisplaySettingParameter displaySettingParameter);
    void updateDisplaySetting(int displaySettingId,DisplaySettingParameter displaySettingParameter);
    void deleteDisplaySetting(int displaySettingId);
}
