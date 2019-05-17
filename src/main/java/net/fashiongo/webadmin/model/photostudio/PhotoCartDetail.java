package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Photo_CartDetail")
@Getter
@Setter
public class PhotoCartDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartDetailID")
    private Integer id;

    @Column(name = "CartID", insertable = false, updatable = false)
    private Integer cartId;

    @Column(name = "StyleName")
    private String styleName;

    @Column(name = "StyleUnitPrice")
    private BigDecimal styleUnitPrice;

    @Column(name = "StyleQty")
    private Integer styleQty;

    @Column(name = "StyleAmount")
    private BigDecimal styleAmount;

    @Column(name = "ColorSetUnitPrice")
    private BigDecimal colorSetUnitPrice;

    @Column(name = "ColorSetQty")
    private Integer colorSetQty;

    @Column(name = "ColorSetAmount")
    private BigDecimal colorSetAmount;

    @Column(name = "ColorUnitPrice")
    private BigDecimal colorUnitPrice;

    @Column(name = "ColorQty")
    private Integer colorQty;

    @Column(name = "ColorAmount")
    private BigDecimal colorAmount;

    @Column(name = "MovieUnitPrice")
    private BigDecimal movieUnitPrice;

    @Column(name = "MovieQty")
    private Integer movieQty;

    @Column(name = "MovieAmount")
    private BigDecimal movieAmount;

    @Column(name = "BaseColorSetUnitPrice")
    private BigDecimal baseColorSetUnitPrice;

    @Column(name = "BaseColorSetQty")
    private Integer baseColorSetQty;

    @Column(name = "BaseColorSetAmount")
    private BigDecimal baseColorSetAmount;

    @Column(name = "ModelSwatchUnitPrice")
    private BigDecimal modelSwatchUnitPrice;

    @Column(name = "ModelSwatchQty")
    private Integer modelSwatchQty;

    @Column(name = "ModelSwatchAmount")
    private BigDecimal modelSwatchAmount;

    @Column(name = "MovieClipUnitPrice")
    private BigDecimal movieClipUnitPrice;

    @Column(name = "MovieClipQty")
    private Integer movieClipQty;

    @Column(name = "MovieClipAmount")
    private BigDecimal movieClipAmount;

    @Column(name = "ColorSwatchUnitPrice")
    private BigDecimal colorSwatchUnitPrice;

    @Column(name = "ColorSwatchQty")
    private Integer colorSwatchQty;

    @Column(name = "ColorSwatchAmount")
    private BigDecimal colorSwatchAmount;

}
