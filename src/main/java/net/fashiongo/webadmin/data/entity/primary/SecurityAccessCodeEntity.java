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
@Table(name = "[security.Access_Code]")
public class SecurityAccessCodeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "CodeID")
    private Integer codeID;

    @Column(name = "AccessCode")
    private String accessCode;

    @Column(name = "ExpiredOn")
    private LocalDateTime expiredOn;
}
