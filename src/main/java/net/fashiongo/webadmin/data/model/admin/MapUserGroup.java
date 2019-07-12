package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MapUserGroup {
    @JsonProperty("MapID")
    private Integer mapID;

    @JsonProperty("UserID")
    private Integer userID;

    @JsonProperty("GroupID")
    private Integer groupID;

    @JsonProperty("GroupName")
    private String groupName;
}
