package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aspnet_Roles")
@Getter
@Setter
public class AspnetRolesEntity {
    @Column(name = "ApplicationId")
    private String applicationId;

    @Id
    @Column(name = "RoleId")
    @Setter(AccessLevel.NONE)
    private String roleId;

    @Column(name = "RoleName")
    private String roleName;

    @Column(name = "LoweredRoleName")
    private String loweredRoleName;

    @Column(name = "Description")
    private String description;
}
