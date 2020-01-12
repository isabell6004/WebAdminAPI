package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "aspnet_UsersInRoles")
@Getter
@IdClass(AspnetUsersInRolesEntityPK.class)
public class AspnetUsersInRolesEntity {
    @Id
    @Column(name = "UserId")
    private String userId;

    @Id
    @Column(name = "RoleId")
    private String roleId;
}
