package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Photo_OrderDetail")
public class PhotoOrderDetail implements IPersistent, Serializable {
	
	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String DEFAULT_TIME = " 00:00:00";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderDetailID")
	private Integer orderDetailID;
	public Integer getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(Integer orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	@Column(name = "OrderID")
	private Integer orderID;
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	@Column(name = "CartDetailID")
	private Integer cartDetailID;
	public Integer getCartDetailID() {
		return cartDetailID;
	}

	public void setCartDetailID(Integer cartDetailID) {
		this.cartDetailID = cartDetailID;
	}

	@Column(name = "StyleName")
	private String styleName;
	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	@Column(name = "PackageID")
	private Integer packageID;
	public Integer getPackageID() {
		return packageID;
	}

	public void setPackageID(Integer packageID) {
		this.packageID = packageID;
	}

	@Column(name = "ColorID")
	private Integer colorID;
	public Integer getColorID() {
		return colorID;
	}

	public void setColorID(Integer colorID) {
		this.colorID = colorID;
	}

	@Column(name = "StyleUnitPrice")
	private BigDecimal styleUnitPrice;
	public BigDecimal getStyleUnitPrice() {
		return styleUnitPrice;
	}

	public void setStyleUnitPrice(BigDecimal styleUnitPrice) {
		this.styleUnitPrice = styleUnitPrice;
	}

	@Column(name = "StyleQty")
	private Integer styleQty;
	public Integer getStyleQty() {
		return styleQty;
	}

	public void setStyleQty(Integer styleQty) {
		this.styleQty = styleQty;
	}

	@Column(name = "StyleAmount")
	private BigDecimal styleAmount;
	public BigDecimal getStyleAmount() {
		return styleAmount;
	}

	public void setStyleAmount(BigDecimal styleAmount) {
		this.styleAmount = styleAmount;
	}

	@Column(name = "ColorSetUnitPrice")
	private BigDecimal colorSetUnitPrice;
	public BigDecimal getColorSetUnitPrice() {
		return colorSetUnitPrice;
	}

	public void setColorSetUnitPrice(BigDecimal colorSetUnitPrice) {
		this.colorSetUnitPrice = colorSetUnitPrice;
	}

	@Column(name = "ColorSetQty")
	private Integer colorSetQty;
	public Integer getColorSetQty() {
		return colorSetQty;
	}

	public void setColorSetQty(Integer colorSetQty) {
		this.colorSetQty = colorSetQty;
	}

	@Column(name = "ColorSetAmount")
	private BigDecimal colorSetAmount;
	public BigDecimal getColorSetAmount() {
		return colorSetAmount;
	}

	public void setColorSetAmount(BigDecimal colorSetAmount) {
		this.colorSetAmount = colorSetAmount;
	}

	@Column(name = "ColorUnitPrice")
	private BigDecimal colorUnitPrice;
	public BigDecimal getColorUnitPrice() {
		return colorUnitPrice;
	}

	public void setColorUnitPrice(BigDecimal colorUnitPrice) {
		this.colorUnitPrice = colorUnitPrice;
	}

	@Column(name = "ColorQty")
	private Integer colorQty;
	public Integer getColorQty() {
		return colorQty;
	}

	public void setColorQty(Integer colorQty) {
		this.colorQty = colorQty;
	}

	@Column(name = "ColorAmount")
	private BigDecimal colorAmount;
	public BigDecimal getColorAmount() {
		return colorAmount;
	}

	public void setColorAmount(BigDecimal colorAmount) {
		this.colorAmount = colorAmount;
	}

	@Column(name = "MovieUnitPrice")
	private BigDecimal movieUnitPrice;
	public BigDecimal getMovieUnitPrice() {
		return movieUnitPrice;
	}

	public void setMovieUnitPrice(BigDecimal movieUnitPrice) {
		this.movieUnitPrice = movieUnitPrice;
	}

	@Column(name = "MovieQty")
	private Integer movieQty;
	public Integer getMovieQty() {
		return movieQty;
	}

	public void setMovieQty(Integer movieQty) {
		this.movieQty = movieQty;
	}

	@Column(name = "MovieAmount")
	private BigDecimal movieAmount;
	public BigDecimal getMovieAmount() {
		return movieAmount;
	}

	public void setMovieAmount(BigDecimal movieAmount) {
		this.movieAmount = movieAmount;
	}

	@JsonIgnore
	@Column(name = "CreatedOn", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOnDate;

	public LocalDateTime getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(LocalDateTime createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	@Transient
	private String createdOn;
	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

	@Column(name = "CreatedBy")
	private String createdBy;
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@JsonIgnore
	@Column(name = "ModifiedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOnDate;

	public LocalDateTime getModifiedOnDate() {
		return modifiedOnDate;
	}

	public void setModifiedOnDate(LocalDateTime modifiedOnDate) {
		this.modifiedOnDate = modifiedOnDate;
	}

	@Transient
	private String modifiedOn;
	public String getModifiedOn() {
		return modifiedOnDate != null ? modifiedOnDate.toString() : null;
	}

	@Column(name = "ModifiedBY")
	private String modifiedBY;
	public String getModifiedBY() {
		return modifiedBY;
	}

	public void setModifiedBY(String modifiedBY) {
		this.modifiedBY = modifiedBY;
	}

}
