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
@Table(name = "Vendor_LambsKey")
@Getter
@Setter
public class VendorLambsKeyEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "LambsKey")
    private String lambsKey;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
