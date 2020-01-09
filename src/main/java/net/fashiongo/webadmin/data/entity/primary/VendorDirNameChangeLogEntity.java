package net.fashiongo.webadmin.data.entity.primary;

import lombok.*;
import org.hibernate.annotations.NotFound;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "VendorDirName_ChangeLog")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class VendorDirNameChangeLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "ChangeID")
    private Integer changeID;

    @Column(name = "SourceDirName")
    private String sourceDirName;

    @Column(name = "TargetDirName")
    private String targetDirName;

    @Column(name = "OldCompanyName")
    private String oldCompanyName;

    @Column(name = "NewCompanyName")
    private String newCompanyName;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    public static VendorDirNameChangeLogEntity create(String sourceDirName, String targetDirName, String oldCompanyName, String newCompanyName) {

        return builder()
                .sourceDirName(sourceDirName)
                .targetDirName(targetDirName)
                .oldCompanyName(oldCompanyName)
                .newCompanyName(newCompanyName)
                .createdOn(LocalDateTime.now()).build();

    }
}
