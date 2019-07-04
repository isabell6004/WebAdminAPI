package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
}
