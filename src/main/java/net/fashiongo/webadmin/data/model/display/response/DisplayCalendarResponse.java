package net.fashiongo.webadmin.data.model.display.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DisplayCalendarResponse {
    private int id;
    private LocalDateTime displayCalendarDate;
    private int displayLocationId;
    private int displaySettingId;
    private String title;
    private int deviceType;
    private int pageId;
}
