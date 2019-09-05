package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "FeaturedItem")
public class FeaturedItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeaturedItemID")
    @Setter(AccessLevel.NONE)
    private Integer featuredItemID;

    @Column(name = "FeaturedItemDate")
    private LocalDateTime featuredItemDate;

    @Column(name = "BestItemUse")
    private Integer bestItemUse;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "WholeSalerName")
    private String wholeSalerName;

    @Column(name = "ProductID")
    private Integer productID;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID", insertable = false, updatable = false)
    private ProductsEntity productsEntity;
}
