package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tblOrders")
public class Order {
    @Id @Column(name = "OrderID") private Integer id;
    @Column(name = "WholeSalerID") private Integer vendorId;
    @Column(name = "RetailerID") private Integer buyerId;
    @Column(name = "TotalAmount") private BigDecimal totalAmount;
    @Column(name = "TotalQty") private Integer totalQty;
    @Column(name = "ConsolidationID") private Integer consolidationId;
    @Column(name = "IsConsolidated") private Boolean isConsolidated;
    @Column(name = "OrderStatusId") private Integer orderStatusId;
    @Column(name = "ShipDate") private LocalDateTime shipDate;
    @Column(name = "ConsolidationShipCharge") private BigDecimal consolidationShipCharge;
    @Column(name = "ShipTrackNo") private String shipTrackNo;
    @Column(name = "ModifiedBy") private String modifiedBy;
    @Column(name = "ModifiedOn") private LocalDateTime modifiedOn;
}
