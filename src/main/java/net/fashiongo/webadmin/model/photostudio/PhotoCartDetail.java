package net.fashiongo.webadmin.model.photostudio;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Photo_CartDetail")
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

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getColorAmount() {
        return colorAmount;
    }

    public void setColorAmount(BigDecimal colorAmount) {
        this.colorAmount = colorAmount;
    }

    public Integer getColorQty() {
        return colorQty;
    }

    public void setColorQty(Integer colorQty) {
        this.colorQty = colorQty;
    }

    public BigDecimal getColorSetAmount() {
        return colorSetAmount;
    }

    public void setColorSetAmount(BigDecimal colorSetAmount) {
        this.colorSetAmount = colorSetAmount;
    }

    public Integer getColorSetQty() {
        return colorSetQty;
    }

    public void setColorSetQty(Integer colorSetQty) {
        this.colorSetQty = colorSetQty;
    }

    public BigDecimal getColorSetUnitPrice() {
        return colorSetUnitPrice;
    }

    public void setColorSetUnitPrice(BigDecimal colorSetUnitPrice) {
        this.colorSetUnitPrice = colorSetUnitPrice;
    }

    public BigDecimal getColorUnitPrice() {
        return colorUnitPrice;
    }

    public void setColorUnitPrice(BigDecimal colorUnitPrice) {
        this.colorUnitPrice = colorUnitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMovieAmount() {
        return movieAmount;
    }

    public void setMovieAmount(BigDecimal movieAmount) {
        this.movieAmount = movieAmount;
    }

    public Integer getMovieQty() {
        return movieQty;
    }

    public void setMovieQty(Integer movieQty) {
        this.movieQty = movieQty;
    }

    public BigDecimal getMovieUnitPrice() {
        return movieUnitPrice;
    }

    public void setMovieUnitPrice(BigDecimal movieUnitPrice) {
        this.movieUnitPrice = movieUnitPrice;
    }

    public BigDecimal getStyleAmount() {
        return styleAmount;
    }

    public void setStyleAmount(BigDecimal styleAmount) {
        this.styleAmount = styleAmount;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public Integer getStyleQty() {
        return styleQty;
    }

    public void setStyleQty(Integer styleQty) {
        this.styleQty = styleQty;
    }

    public BigDecimal getStyleUnitPrice() {
        return styleUnitPrice;
    }

    public void setStyleUnitPrice(BigDecimal styleUnitPrice) {
        this.styleUnitPrice = styleUnitPrice;
    }

}
