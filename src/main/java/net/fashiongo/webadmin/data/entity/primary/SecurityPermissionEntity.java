package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "security.Permission")
public class SecurityPermissionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "PermissionID")
    private Integer permissionID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "ResourceID")
    private Integer resourceID;

    @Column(name = "Allow")
    private Boolean allow;

    @Column(name = "AllowEdit")
    private Boolean allowEdit;

    @Column(name = "AllowDelete")
    private Boolean allowDelete;

    @Column(name = "AllowAdd")
    private Boolean allowAdd;

    @Column(name = "ApplicationID")
    private Integer applicationID;
}
