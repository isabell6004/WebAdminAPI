package net.fashiongo.webadmin.data.model.display;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Getter
@Setter
public class DisplaySettingRequest {
    @NotNull(message = "displayLocationId is required")
    private int displayLocationId;
    @NotNull(message = "linkType is required")
    private int linkType;
    private Integer linkCollectionId;
    private String linkFileName;
    private String linkUrl;
    @NotNull(message = "title is required")
    private String title;
    @NotNull(message = "startDate is required")
    private LocalDateTime startDate;
    @NotNull(message = "endDate is required")
    private LocalDateTime endDate;
}
