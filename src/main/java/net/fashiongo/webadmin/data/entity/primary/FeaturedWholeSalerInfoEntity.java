package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "fg_stats.dbo.Featured_WholeSaler_Info")
@Getter
public class FeaturedWholeSalerInfoEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "VendorRate")
    private BigDecimal vendorRate;

    @Column(name = "BuyerRate")
    private BigDecimal buyerRate;

    @Column(name = "CheckOutAmount")
    private BigDecimal checkOutAmount;

    @Column(name = "CheckOutQty")
    private Integer checkOutQty;

    @Column(name = "TotalADAmount")
    private BigDecimal totalAdAmount;

    @Column(name = "VendorTierGroup")
    private String vendorTierGroup;

    @Column(name = "isCM")
    private Boolean isCM;

    @Column(name = "isPG")
    private Boolean isPG;

    @Column(name = "isCS")
    private Boolean isCS;
}
