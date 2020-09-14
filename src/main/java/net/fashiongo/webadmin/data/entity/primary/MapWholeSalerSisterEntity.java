package net.fashiongo.webadmin.data.entity.primary;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "Map_WholeSaler_Sister")
public class MapWholeSalerSisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "MapID")
    private Integer mapID;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "SisterWholeSalerID")
    private Integer sisterWholeSalerID;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SisterWholeSalerID", referencedColumnName = "vendor_id", updatable = false, insertable = false)
    private VendorEntity wholeSaler;
//    private WholeSalerEntity wholeSaler;

    public static MapWholeSalerSisterEntity create(Integer vendorId, Integer sisterVendorId, String username) {
        return builder()
                .wholeSalerID(vendorId)
                .sisterWholeSalerID(sisterVendorId)
                .createdOn(LocalDateTime.now())
                .createdBy(username)
                .build();
    }
}
