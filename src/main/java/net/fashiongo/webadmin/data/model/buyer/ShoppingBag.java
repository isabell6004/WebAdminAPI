package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class ShoppingBag {

	@JsonProperty("AvailableOn")
	private LocalDateTime availableOn;

	@JsonProperty("BQ1")
	private Integer bQ1;

	@JsonProperty("BQ2")
	private Integer bQ2;

	@JsonProperty("BQ3")
	private Integer bQ3;

	@JsonProperty("BQ4")
	private Integer bQ4;

	@JsonProperty("BQ5")
	private Integer bQ5;

	@JsonProperty("BQ6")
	private Integer bQ6;

	@JsonProperty("BQ7")
	private Integer bQ7;

	@JsonProperty("BQ8")
	private Integer bQ8;

	@JsonProperty("BQ9")
	private Integer bQ9;

	@JsonProperty("BQ10")
	private Integer bQ10;

	@JsonProperty("BQ11")
	private Integer bQ11;

	@JsonProperty("CartID")
	private Integer cartID;

	@JsonProperty("ColorName")
	private String colorName;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("DirName")
	private String dirName;

	@JsonProperty("EvenColorYN")
	private String evenColorYN;

	@JsonProperty("ExtraCharge")
	private BigDecimal extraCharge;

	@JsonProperty("ExtraChargeAmountTo")
	private BigDecimal extraChargeAmountTo;

	@JsonProperty("ImageUrlRoot")
	private String imageUrlRoot;

	@JsonProperty("NoOfPack")
	private Integer noOfPack;

	@JsonProperty("PackQty1")
	private Integer packQty1;

	@JsonProperty("PackQty2")
	private Integer packQty2;

	@JsonProperty("PackQty3")
	private Integer packQty3;

	@JsonProperty("PackQty4")
	private Integer packQty4;

	@JsonProperty("PackQty5")
	private Integer packQty5;

	@JsonProperty("PackQty6")
	private Integer packQty6;

	@JsonProperty("PackQty7")
	private Integer packQty7;

	@JsonProperty("PackQty8")
	private Integer packQty8;

	@JsonProperty("PackQty9")
	private Integer packQty9;

	@JsonProperty("PackQty10")
	private Integer packQty10;

	@JsonProperty("PackQty11")
	private Integer packQty11;

	@JsonProperty("PackQtyTotal")
	private Integer packQtyTotal;

	@JsonProperty("PictureGeneral")
	private String pictureGeneral;

	@JsonProperty("PictureLogo")
	private String pictureLogo;

	@JsonProperty("PrePackYN")
	private String prePackYN;

	@JsonProperty("ProductID")
	private Integer productID;

	@JsonProperty("ProductName")
	private String productName;

	@JsonProperty("RetailerID")
	private Integer retailerID;

	@JsonProperty("S1")
	private String s1;

	@JsonProperty("S2")
	private String s2;

	@JsonProperty("S")
	private String s3;

	@JsonProperty("S4")
	private String s4;

	@JsonProperty("S5")
	private String s5;

	@JsonProperty("S6")
	private String s6;

	@JsonProperty("S7")
	private String s7;

	@JsonProperty("S8")
	private String s8;

	@JsonProperty("S9")
	private String s9;

	@JsonProperty("S10")
	private String s10;

	@JsonProperty("S11")
	private String s11;

	@JsonProperty("SubTotal")
	private BigDecimal subTotal;

	@JsonProperty("TotalQty")
	private Integer totalQty;

	@JsonProperty("UnitPrice")
	private BigDecimal unitPrice;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	public ShoppingBag(Date availableOn, Integer bQ1, Integer bQ2, Integer bQ3, Integer bQ4, Integer bQ5, Integer bQ6, Integer bQ7, Integer bQ8, Integer bQ9, Integer bQ10, Integer bQ11, Integer cartID, String colorName, String companyName, String dirName, String evenColorYN, BigDecimal extraCharge, BigDecimal extraChargeAmountTo, String imageUrlRoot, Integer noOfPack, Integer packQty1, Integer packQty2, Integer packQty3, Integer packQty4, Integer packQty5, Integer packQty6, Integer packQty7, Integer packQty8, Integer packQty9, Integer packQty10, Integer packQty11, Integer packQtyTotal, String pictureGeneral, String pictureLogo, Character prePackYN, Integer productID, String productName, Integer retailerID, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, BigDecimal subTotal, Integer totalQty, BigDecimal unitPrice, Integer wholeSalerID) {
		this.availableOn = Optional.ofNullable(availableOn).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		this.bQ1 = bQ1;
		this.bQ2 = bQ2;
		this.bQ3 = bQ3;
		this.bQ4 = bQ4;
		this.bQ5 = bQ5;
		this.bQ6 = bQ6;
		this.bQ7 = bQ7;
		this.bQ8 = bQ8;
		this.bQ9 = bQ9;
		this.bQ10 = bQ10;
		this.bQ11 = bQ11;
		this.cartID = cartID;
		this.colorName = colorName;
		this.companyName = companyName;
		this.dirName = dirName;
		this.evenColorYN = evenColorYN;
		this.extraCharge = extraCharge;
		this.extraChargeAmountTo = extraChargeAmountTo;
		this.imageUrlRoot = imageUrlRoot;
		this.noOfPack = noOfPack;
		this.packQty1 = packQty1;
		this.packQty2 = packQty2;
		this.packQty3 = packQty3;
		this.packQty4 = packQty4;
		this.packQty5 = packQty5;
		this.packQty6 = packQty6;
		this.packQty7 = packQty7;
		this.packQty8 = packQty8;
		this.packQty9 = packQty9;
		this.packQty10 = packQty10;
		this.packQty11 = packQty11;
		this.packQtyTotal = packQtyTotal;
		this.pictureGeneral = pictureGeneral;
		this.pictureLogo = pictureLogo;
		this.prePackYN = Optional.ofNullable(prePackYN).map(character -> character.toString()).orElse(null);
		this.productID = productID;
		this.productName = productName;
		this.retailerID = retailerID;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.s5 = s5;
		this.s6 = s6;
		this.s7 = s7;
		this.s8 = s8;
		this.s9 = s9;
		this.s10 = s10;
		this.s11 = s11;
		this.subTotal = subTotal;
		this.totalQty = totalQty;
		this.unitPrice = unitPrice;
		this.wholeSalerID = wholeSalerID;
	}

	public LocalDateTime getAvailableOn() {
		return availableOn;
	}

	public Integer getbQ1() {
		return bQ1;
	}

	public Integer getbQ2() {
		return bQ2;
	}

	public Integer getbQ3() {
		return bQ3;
	}

	public Integer getbQ4() {
		return bQ4;
	}

	public Integer getbQ5() {
		return bQ5;
	}

	public Integer getbQ6() {
		return bQ6;
	}

	public Integer getbQ7() {
		return bQ7;
	}

	public Integer getbQ8() {
		return bQ8;
	}

	public Integer getbQ9() {
		return bQ9;
	}

	public Integer getbQ10() {
		return bQ10;
	}

	public Integer getbQ11() {
		return bQ11;
	}

	public Integer getCartID() {
		return cartID;
	}

	public String getColorName() {
		return colorName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getDirName() {
		return dirName;
	}

	public String getEvenColorYN() {
		return evenColorYN;
	}

	public BigDecimal getExtraCharge() {
		return extraCharge;
	}

	public BigDecimal getExtraChargeAmountTo() {
		return extraChargeAmountTo;
	}

	public String getImageUrlRoot() {
		return imageUrlRoot;
	}

	public Integer getNoOfPack() {
		return noOfPack;
	}

	public Integer getPackQty1() {
		return packQty1;
	}

	public Integer getPackQty2() {
		return packQty2;
	}

	public Integer getPackQty3() {
		return packQty3;
	}

	public Integer getPackQty4() {
		return packQty4;
	}

	public Integer getPackQty5() {
		return packQty5;
	}

	public Integer getPackQty6() {
		return packQty6;
	}

	public Integer getPackQty7() {
		return packQty7;
	}

	public Integer getPackQty8() {
		return packQty8;
	}

	public Integer getPackQty9() {
		return packQty9;
	}

	public Integer getPackQty10() {
		return packQty10;
	}

	public Integer getPackQty11() {
		return packQty11;
	}

	public Integer getPackQtyTotal() {
		return packQtyTotal;
	}

	public String getPictureGeneral() {
		return pictureGeneral;
	}

	public String getPictureLogo() {
		return pictureLogo;
	}

	public String getPrePackYN() {
		return prePackYN;
	}

	public Integer getProductID() {
		return productID;
	}

	public String getProductName() {
		return productName;
	}

	public Integer getRetailerID() {
		return retailerID;
	}

	public String getS1() {
		return s1;
	}

	public String getS2() {
		return s2;
	}

	public String getS3() {
		return s3;
	}

	public String getS4() {
		return s4;
	}

	public String getS5() {
		return s5;
	}

	public String getS6() {
		return s6;
	}

	public String getS7() {
		return s7;
	}

	public String getS8() {
		return s8;
	}

	public String getS9() {
		return s9;
	}

	public String getS10() {
		return s10;
	}

	public String getS11() {
		return s11;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public Integer getTotalQty() {
		return totalQty;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
}
