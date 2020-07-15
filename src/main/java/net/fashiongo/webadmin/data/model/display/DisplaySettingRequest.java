package net.fashiongo.webadmin.data.model.display;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class DisplaySettingRequest {

    private int displayLocationId;
    private int linkType;
    private Integer linkCollectionId;
    private String linkFileName;
    private String linkUrl;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
