package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "vendor_email")
public class VendorEmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_email_id")
    private Long id;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "email_type_code")
    private Integer typeCode;

    @Column(name = "email")
    private String email;

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
            if (this.vendorEntity != null) {
                this.vendorEntity.getName();
            }
        } catch (EntityNotFoundException e) {
            return null;
        }
        return vendorEntity;
    }
}
