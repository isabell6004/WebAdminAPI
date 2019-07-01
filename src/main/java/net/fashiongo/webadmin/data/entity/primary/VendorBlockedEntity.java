package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Vendor_Blocked")
public class VendorBlockedEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BlockID")
    @Setter(AccessLevel.NONE)
    private Integer blockId;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerId;

    @Column(name = "BlockReasonID")
    private Integer blockReasonId;

    @Column(name = "BlockedOn")
    private LocalDateTime blockedOn;

    @Column(name = "BlockedBy")
    private String blockedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WholeSalerID", updatable = false, insertable = false)
    private ReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BlockReasonID", updatable = false, insertable = false)
    private ListVendorBlockReasonEntity listVendorBlockReasonEntity;
}
