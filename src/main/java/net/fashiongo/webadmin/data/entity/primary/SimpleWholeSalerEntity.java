package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tblWholeSaler")
public class SimpleWholeSalerEntity implements Serializable {

    @Id
    @Column(name = "WholeSalerID")
    private Integer wholeSalerId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "CompanyType")
    private String companyType;

    @Column(name = "DirName")
    private String dirName;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "ShopActive")
    private Boolean shopActive;

    @Column(name = "OrderActive")
    private Boolean orderActive;

    @Column(name = "ImageServerID")
    private Integer imageServerID;

    @Column(name = "AllowImage2Anony")
    private boolean allowImage2Anony = true;

    @Column(name = "AllowImmediateShopping")
    private boolean allowImmediateShopping = true;

    @Column(name = "AllowAccess2Y3")
    private boolean allowAccess2Y3 = true;

    @Column(name = "Vendor_Type")
    private int vendorType = 1;

    @Column(name = "FashionGoExclusive")
    private Boolean fashionGoExclusive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ImageServerID", insertable = false, updatable = false)
    private SystemImageServersEntity systemImageServersEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WholeSalerID",referencedColumnName = "VendorID", insertable = false, updatable = false)
    private MapWaUserVendorEntity mapWaUserVendor;
}
