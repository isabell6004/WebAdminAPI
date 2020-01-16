package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VendorName_History")
@Getter
@Setter
public class VendorNameHistoryEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "NameHistory")
    private String nameHistory;
}
