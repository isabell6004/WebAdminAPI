package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tblOrders")
public class Order {
    @Id @Column(name = "OrderID") private Integer id;
    @Column(name = "TotalAmount") private BigDecimal totalAmount;
    @Column(name = "TotalQty") private Integer totalQty;
    @Column(name = "ConsolidationID") private Integer consolidationId;
    @Column(name = "IsConsolidated") private Boolean isConsolidated;
    @Column(name = "OrderStatusId") private Integer orderStatusId;
}
