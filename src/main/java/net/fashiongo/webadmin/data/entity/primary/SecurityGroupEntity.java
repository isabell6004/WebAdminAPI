package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "[security.Group]")
public class SecurityGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "GroupID")
    private Integer groupID;

    @Column(name = "GroupName")
    private String groupName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Active")
    private boolean active;
}
