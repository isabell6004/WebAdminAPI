package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "vendor_industry")
public class VendorIndustryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_industry_id")
    private Long id;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_industry_code")
    private Integer typeCode;

    @Column(name = "created_on", updatable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdOn;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "modified_on")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", updatable = false, insertable = false)
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
