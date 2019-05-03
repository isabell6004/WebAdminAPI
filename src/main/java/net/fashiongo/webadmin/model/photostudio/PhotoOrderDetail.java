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

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Photo_OrderDetail")
@Getter
@Setter
public class PhotoOrderDetail implements IPersistent, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderDetailID")
	private Integer orderDetailID;

	@Column(name = "OrderID")
	private Integer orderID;

	@Column(name = "CartDetailID")
	private Integer cartDetailID;

	@Column(name = "StyleName")
	private String styleName;

	// OLD
	@Column(name = "StyleUnitPrice")
	private BigDecimal styleUnitPrice;

	@Column(name = "StyleQty")
	private Integer styleQty;

	@Column(name = "StyleAmount", insertable = false, updatable = false)
	private BigDecimal styleAmount;

	@Column(name = "ColorSetUnitPrice")
	private BigDecimal colorSetUnitPrice;

	@Column(name = "ColorSetQty")
	private Integer colorSetQty;

	@Column(name = "ColorSetAmount", insertable = false, updatable = false)
	private BigDecimal colorSetAmount;

	@Column(name = "ColorUnitPrice")
	private BigDecimal colorUnitPrice;

	@Column(name = "ColorQty")
	private Integer colorQty;

	@Column(name = "ColorAmount", insertable = false, updatable = false)
	private BigDecimal colorAmount;

	@Column(name = "MovieUnitPrice")
	private BigDecimal movieUnitPrice;

	@Column(name = "MovieQty")
	private Integer movieQty;

	@Column(name = "MovieAmount", insertable = false, updatable = false)
	private BigDecimal movieAmount;

	// NEW
    @Column(name = "BaseColorSetUnitPrice")
    private BigDecimal baseColorSetUnitPrice;

    @Column(name = "BaseColorSetQty")
    private Integer baseColorSetQty;

    @Column(name = "BaseColorSetAmount", insertable = false, updatable = false)
    private BigDecimal baseColorSetAmount;

    @Column(name = "ColorSwatchUnitPrice")
    private BigDecimal colorSwatchUnitPrice;

    @Column(name = "ColorSwatchQty")
    private Integer colorSwatchQty;

    @Column(name = "ColorSwatchAmount", insertable = false, updatable = false)
    private BigDecimal colorSwatchAmount;

    @Column(name = "ModelSwatchUnitPrice")
    private BigDecimal modelSwatchUnitPrice;

    @Column(name = "ModelSwatchQty")
    private Integer modelSwatchQty;

    @Column(name = "ModelSwatchAmount", insertable = false, updatable = false)
    private BigDecimal modelSwatchAmount;

    @Column(name = "MovieClipUnitPrice")
    private BigDecimal movieClipUnitPrice;

    @Column(name = "MovieClipQty")
    private Integer movieClipQty;

    @Column(name = "MovieClipAmount", insertable = false, updatable = false)
    private BigDecimal movieClipAmount;

    @JsonIgnore
	@Column(name = "CreatedOn", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOnDate;

	@JsonIgnore
	@Transient
	private String createdOn;
	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

	@JsonIgnore
	@Column(name = "CreatedBy")
	private String createdBy;

	@JsonIgnore
	@Column(name = "ModifiedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOnDate;

	@JsonIgnore
	@Transient
	private String modifiedOn;
	public String getModifiedOn() {
		return modifiedOnDate != null ? modifiedOnDate.toString() : null;
	}

	@Column(name = "ModifiedBY")
	private String modifiedBY;
}
