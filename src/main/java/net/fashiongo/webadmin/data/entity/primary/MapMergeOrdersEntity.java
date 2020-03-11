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

@Entity
@Table(name = "Map_Merge_Orders")
@Getter
@Setter
public class MapMergeOrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "MapID")
    private Integer mapID;

    @Column(name = "MergeID")
    private Integer mergeID;

    @Column(name = "OrderID")
    private Integer orderID;
}
