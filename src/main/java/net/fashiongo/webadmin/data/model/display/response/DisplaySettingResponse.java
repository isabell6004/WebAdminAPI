package net.fashiongo.webadmin.data.model.display.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DisplaySettingResponse {
    private int id;
    private int displayLocationId;
    private int linkType;
    private Integer linkCollectionId;
    private String linkFileName;
    private String linkUrl;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isDelete;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
    private Integer displaySettingStatus;
}
