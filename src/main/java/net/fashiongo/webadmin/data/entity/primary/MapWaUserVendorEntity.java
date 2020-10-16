package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Map_Wa_User_Vendor")
public class MapWaUserVendorEntity {

    @Id
    @Column(name = "MapID")
    private Integer mapID;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "VendorID")
    private int vendorID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VendorID", referencedColumnName = "vendor_id", updatable = false, insertable = false)
    private VendorEntity vendorEntity;

    public VendorEntity getVendorEntity() {
        try {
            vendorEntity.getVendor_id();
        } catch (EntityNotFoundException e) {
            return null;
        }
        return vendorEntity;
    }
}
