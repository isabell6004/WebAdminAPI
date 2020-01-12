package net.fashiongo.webadmin.data.entity.primary;

import lombok.Data;

import java.io.Serializable;

@Data
public class AspnetUsersInRolesEntityPK implements Serializable {
    private String userId;
    private String roleId;
}
