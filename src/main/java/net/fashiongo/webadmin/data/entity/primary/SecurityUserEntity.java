package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "[security.User]")
public class SecurityUserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "UserGUID")
    private String userGUID;

    @Column(name = "Role")
    private String role;

    @Column(name = "Active")
    private boolean active;

    @Column(name = "IPTimeExempt")
    private Boolean ipTimeExempt;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "Email")
    private String email;

    @Column(name = "DataAccessLevel")
    private Integer dataAccessLevel;
}
