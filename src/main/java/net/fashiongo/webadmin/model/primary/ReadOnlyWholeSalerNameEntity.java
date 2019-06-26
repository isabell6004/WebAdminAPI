package net.fashiongo.webadmin.model.primary;

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
public class ReadOnlyWholeSalerNameEntity implements Serializable {

    @Id
    @Column(name = "WholeSalerID")
    private Integer wholeSalerId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "CompanyName")
    private String companyName;

}