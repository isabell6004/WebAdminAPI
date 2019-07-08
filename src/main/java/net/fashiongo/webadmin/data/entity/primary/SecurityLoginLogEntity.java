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
@Table(name = "[security.Login_Log]")
public class SecurityLoginLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "LogID")
    private Integer logID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "IP")
    private String ip;

    @Column(name = "LoginOn")
    private LocalDateTime loginOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", insertable = false, updatable = false)
    private SecurityUserEntity securityUserEntity;
}
