package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class ProductInfo {
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("ProductName2")
	private String productName2;
	@JsonProperty("ProductDescription")
	private String productDescription;
	@JsonProperty("UnitPrice")
	private BigDecimal unitPrice;
	@JsonProperty("UnitPrice1")
	private BigDecimal unitPrice1;
	@JsonProperty("UnitPrice2")
	private BigDecimal unitPrice2;
	@JsonProperty("PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("PatternID")
	private Integer patternID;
	@JsonProperty("LengthID")
	private Integer lengthID;
	@JsonProperty("StyleID")
	private Integer styleID;
	@JsonProperty("FabricDescription")
	private String fabricDescription;
	@JsonProperty("MadeIn")
	private String madeIn;
	@JsonProperty("StockAvailability")
	private String stockAvailability;
	@JsonProperty("ItemName")
	private String ItemName;
	@JsonProperty("AvailableOn")
	private LocalDateTime availableOn;
	@JsonProperty("Labeled")
	private String labeled;
	
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductName2() {
		return productName2;
	}
	public void setProductName2(String productName2) {
		this.productName2 = productName2;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getUnitPrice1() {
		return unitPrice1;
	}
	public void setUnitPrice1(BigDecimal unitPrice1) {
		this.unitPrice1 = unitPrice1;
	}
	public BigDecimal getUnitPrice2() {
		return unitPrice2;
	}
	public void setUnitPrice2(BigDecimal unitPrice2) {
		this.unitPrice2 = unitPrice2;
	}
	public String getPictureGeneral() {
		return pictureGeneral;
	}
	public void setPictureGeneral(String pictureGeneral) {
		this.pictureGeneral = pictureGeneral;
	}
	public Integer getPatternID() {
		return patternID;
	}
	public void setPatternID(Integer patternID) {
		this.patternID = patternID;
	}
	public Integer getLengthID() {
		return lengthID;
	}
	public void setLengthID(Integer lengthID) {
		this.lengthID = lengthID;
	}
	public Integer getStyleID() {
		return styleID;
	}
	public void setStyleID(Integer styleID) {
		this.styleID = styleID;
	}
	public String getFabricDescription() {
		return fabricDescription;
	}
	public void setFabricDescription(String fabricDescription) {
		this.fabricDescription = fabricDescription;
	}
	public String getMadeIn() {
		return madeIn;
	}
	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}
	public String getStockAvailability() {
		return stockAvailability;
	}
	public void setStockAvailability(String stockAvailability) {
		this.stockAvailability = stockAvailability;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public LocalDateTime getAvailableOn() {
		return availableOn;
	}
	public void setAvailableOn(LocalDateTime availableOn) {
		this.availableOn = availableOn;
	}
	public String getLabeled() {
		return labeled;
	}
	public void setLabeled(String labeled) {
		this.labeled = labeled;
	}
}
