package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "VendorName_History_Log")
@Setter
@Getter
public class VendorNameHistoryLogEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "OldCompanyName")
    private String oldCompanyName;

    @Column(name = "NewCompanyName")
    private String newCompanyName;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "ChangeID")
    private Integer changeID;
}
