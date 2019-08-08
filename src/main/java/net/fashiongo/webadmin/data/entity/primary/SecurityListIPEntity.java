package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "[security.List_IP]")
public class SecurityListIPEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "IPID")
    private Integer ipID;

    @Column(name = "IPAddress")
    private String ipAddress;

    @Column(name = "Description")
    private String description;
}
