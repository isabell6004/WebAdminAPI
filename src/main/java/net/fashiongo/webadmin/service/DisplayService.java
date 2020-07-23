package net.fashiongo.webadmin.service;


import net.fashiongo.webadmin.data.model.display.DisplaySettingRequest;
import net.fashiongo.webadmin.data.model.display.response.*;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

public interface DisplayService {
    CollectionObject<DisplayLocationResponse> getDisplayLocations();
    CollectionObject<DisplayCollectionResponse> getDisplayCollections();
    CollectionObject<DisplayCalendarResponse> getDisplayCalendar(LocalDateTime startDate, LocalDateTime endDate);
    SingleObject<DisplaySettingResponse> getDisplaySetting(int displaySettingId);
    SingleObject<Integer> createDisplaySetting(DisplaySettingRequest displaySettingRequest, MultipartFile imageLinkFile) throws IOException;
    void updateDisplaySetting(int displaySettingId, DisplaySettingRequest displaySettingRequest, MultipartFile imageLinkFile) throws IOException;
    void deleteDisplaySetting(int displaySettingId);
    CollectionObject<DisplaySettingListResponse> getDisplaySettingList(int pageNum,
                                                                       int pageSize,
                                                                       Integer deviceType,
                                                                       Integer pageId,
                                                                       Integer displayLocationId,
                                                                       Integer linkType,
                                                                       Integer dateType,
                                                                       LocalDateTime fromDate,
                                                                       LocalDateTime toDate,
                                                                       Integer displaySettingStatus,
                                                                       String title,
                                                                       Integer linkCollectionId);
}
