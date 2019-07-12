package net.fashiongo.webadmin.data.model.admin;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResultGetSecurityUserGroupAccesstimes {
    List<LoginControl> loginControls;

    List<MapUserGroup> mapUserGroups;
}
