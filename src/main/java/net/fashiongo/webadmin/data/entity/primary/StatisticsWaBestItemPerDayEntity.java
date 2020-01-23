package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Statistics_Wa_BestItemPerDay")
@Getter
@Setter
@IdClass(StatisticsWaBestItemPerDayEntityPK.class)
public class StatisticsWaBestItemPerDayEntity {
    @Id
    @Column(name = "StartDate")
    @Setter(AccessLevel.NONE)
    private Date startDate;

    @Id
    @Column(name = "ProductID")
    @Setter(AccessLevel.NONE)
    private Integer productID;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "CategoryID")
    private Integer categoryID;

    @Column(name = "ParentCategoryID")
    private Integer parentCategoryID;

    @Column(name = "ParentParentCategoryID")
    private Integer parentParentCategoryID;

    @Column(name = "OrderQty")
    private Integer orderQty;

    @Column(name = "OrderItemQty")
    private Integer orderItemQty;

    @Column(name = "OrderAmount")
    private BigDecimal orderAmount;

    @Column(name = "CreatedOn")
    private Timestamp createdOn;
}
