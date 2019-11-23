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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Ad_Vendor_Item")
@Getter
@Setter
public class AdVendorItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "AdVendorItemID")
    private Integer adVendorItemID;

    @Column(name = "CategoryID")
    private Integer categoryID;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "FromDate")
    private LocalDateTime fromDate;

    @Column(name = "ToDate")
    private LocalDateTime toDate;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "IsConfirmed")
    private Boolean isConfirmed;

    @Column(name = "IsConfirmedDate")
    private LocalDateTime isConfirmedDate;

    @Column(name = "ActivatedOn")
    private LocalDateTime activatedOn;

    @Column(name = "ActivatedBy")
    private String activatedBy;

    @Column(name = "HowToInput")
    private Integer howToInput;

    @Column(name = "HowToSell")
    private Integer howToSell;

    @Column(name = "InvoiceOut")
    private Boolean invoiceOut;

    @Column(name = "ItemCount")
    private Integer itemCount;

    @Column(name = "ActualPrice")
    private BigDecimal actualPrice;

    @Column(name = "TransferredToCS")
    private LocalDateTime transferredToCS;
}
