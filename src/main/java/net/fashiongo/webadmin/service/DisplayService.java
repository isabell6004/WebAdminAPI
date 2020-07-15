package net.fashiongo.webadmin.service;


import net.fashiongo.webadmin.data.model.display.DisplaySettingRequest;
import net.fashiongo.webadmin.data.model.display.response.DisplayCalendarResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplayCollectionResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplayLocationResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplaySettingResponse;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface DisplayService {
    CollectionObject<DisplayLocationResponse> getDisplayLocations();
    CollectionObject<DisplayCollectionResponse> getDisplayCollections();
    CollectionObject<DisplayCalendarResponse> getDisplayCalendar(LocalDateTime startDate, LocalDateTime endDate);
    SingleObject<DisplaySettingResponse> getDisplaySetting(int displaySettingId);
    SingleObject<Integer> createDisplaySetting(DisplaySettingRequest displaySettingRequest);
    void updateDisplaySetting(int displaySettingId,DisplaySettingRequest displaySettingRequest) throws IOException;
    void deleteDisplaySetting(int displaySettingId);
}
