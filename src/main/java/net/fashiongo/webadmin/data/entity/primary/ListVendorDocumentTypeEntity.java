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
@Getter
@Setter
@Table(name = "List_VendorDocumentType")
public class ListVendorDocumentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "VendorDocumentTypeID")
    private Integer vendorDocumentTypeID;

    @Column(name = "VendorDocumentType")
    private String vendorDocumentType;
}
