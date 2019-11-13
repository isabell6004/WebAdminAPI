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

@Entity
@Table(name = "Code_VendorCapType")
@Getter
@Setter
public class CodeVendorCapTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "VendorCapTypeID")
    private Integer vendorCapTypeID;

    @Column(name = "VendorCapTypeName")
    private String vendorCapTypeName;

    @Column(name = "DefaultCap")
    private Integer defaultCap;
}
