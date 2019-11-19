package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Code_VendorIndustry")
@Setter
@Getter
public class CodeVendorIndustryEntity {
    @Id
    @Column(name = "VendorIndustryID")
    private Integer vendorIndustryID;

    @Column(name = "VendorIndustryName")
    private String vendorIndustryName;

    @Column(name = "Active")
    private Boolean active;
}
