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
@Table(name = "VendorCap")
@Getter
@Setter
public class VendorCapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "VendorCapID")
    private Integer vendorCapID;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "VendorCapTypeID")
    private Integer vendorCapTypeID;

    @Column(name = "Cap")
    private Integer cap;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
