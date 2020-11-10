package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "vendor_banner")
public class VendorBannerEntity {
    @Id
    @Column(name = "vendor_banner_id")
    private Long id;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "banner_type_id")
    private Long bannerTypeId;

    @Column(name = "request_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime requestDate;

    @Column(name = "request_by")
    private String requestBy;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "rejected_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime rejectedDate;

    @Column(name = "rejected_by")
    private String rejectedBy;

    @Column(name = "reject_reason")
    private String rejectReason;

    @Column(name = "decided_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime decidedDate;

    @Column(name = "decided_by")
    private String decidedBy;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "original_file_name")
    private String fileName;

    @Column(name = "deleted_on")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime deletedOn;

    @Column(name = "deleted_by")
    private String deletedBy;

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

    public VendorEntity getVendorEntity(){
        try{
            if (this.vendorEntity != null) {
                this.vendorEntity.getName();
            }
        }
        catch(EntityNotFoundException e){
            return null;
        }
        return vendorEntity;
    }
}
