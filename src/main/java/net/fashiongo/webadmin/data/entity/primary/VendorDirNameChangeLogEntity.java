package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "VendorDirName_ChangeLog")
@Getter
@Setter
public class VendorDirNameChangeLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
