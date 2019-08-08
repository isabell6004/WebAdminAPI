package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "[security.Map_User_Group]")
public class SecurityMapUserGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "MapID")
    private Integer mapID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "GroupID")
    private Integer groupID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GroupID", updatable = false, insertable = false)
    private SecurityGroupEntity securityGroupEntity;
}
