package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "InventoryID")
    @Setter(AccessLevel.NONE)
    private Integer inventoryID;

    @Column(name = "ProductID")
    private Integer productID;

    @Column(name = "ColorID")
    private Integer colorID;

    @Column(name = "Available")
    private Boolean available;

    @Column(name = "AvailableQty")
    private Integer availableQty;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "SizeName")
    private String sizeName;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "StatusCode")
    private Integer statusCode;

    @Column(name = "Threshold")
    private Integer threshold;

    @Column(name = "AvailableOn")
    private LocalDateTime availableOn;

    @Column(name = "MarkIfLess")
    private Integer markifLess;

    @Column(name = "LowIfLess")
    private Integer lowIfLess;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ColorID", updatable = false, insertable = false)
    private XColorEntity xColors;
}
