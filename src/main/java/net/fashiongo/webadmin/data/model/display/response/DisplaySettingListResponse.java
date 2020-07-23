package net.fashiongo.webadmin.data.model.display.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DisplaySettingListResponse {
    private Integer displaySettingId;
    private String title;
    private Integer deviceType;
    private Integer pageId;
    private String pageName;
    private Integer displayLocationId;
    private String locationName;
    private Integer linkType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdOn;
    private String createdBy;
    private Integer displaySettingStatus;
}

