package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "[security.Login_Control]")
public class SecurityLoginControlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "ControlID")
    private Integer controlID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "Weekday")
    private Integer weekday;

    @Column(name = "TimeFrom")
    private LocalDateTime timeFrom;

    @Column(name = "TimeTo")
    private LocalDateTime timeTo;

    @Column(name = "Allow")
    private Boolean allow;

    @Column(name = "Active")
    private boolean active;
}
