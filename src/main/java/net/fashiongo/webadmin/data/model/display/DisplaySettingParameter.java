package net.fashiongo.webadmin.data.model.display;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Accessors(fluent = true)
public class DisplaySettingParameter {
    @JsonProperty(value = "displayLocationId")
    private int displayLocationId;

    @JsonProperty(value = "linkType")
    private int linkType;

    @JsonProperty(value = "linkCollectionId")
    private Integer linkCollectionId;

    @JsonProperty(value = "linkFileName")
    private String linkFileName;

    @JsonProperty(value = "linkUrl")
    private String linkUrl;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "startDate")
    private String startDate;

    @JsonProperty(value = "endDate")
    private String endDate;

}
