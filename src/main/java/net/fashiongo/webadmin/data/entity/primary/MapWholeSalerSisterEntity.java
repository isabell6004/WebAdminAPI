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

@Entity
@Table(name = "Map_WholeSaler_Sister")
@Getter
@Setter
public class MapWholeSalerSisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "SisterWholeSalerID", referencedColumnName = "WholeSalerID", updatable = false, insertable = false)
    private WholeSalerEntity wholeSaler;
}
