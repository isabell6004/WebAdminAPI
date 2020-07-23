package net.fashiongo.webadmin.data.model.display.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DisplayLocationResponse {

    private int id;
    private int deviceType;
    private int pageId;
    private String pageName;
    private String locationName;
    private int displayType;
    private int linkType;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
