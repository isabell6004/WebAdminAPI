package net.fashiongo.webadmin.data.model.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.admin.LoginControl;
import net.fashiongo.webadmin.data.model.admin.MapUserGroup;

import java.util.List;

@Getter
@Builder
public class GetSecurityUserGroupAccesstimeResponse {
    @JsonProperty("Table")
    private List<MapUserGroup> mapUserGroupList;

    @JsonProperty("Table1")
    private List<LoginControl> loginControlList;

    private boolean success;
}
