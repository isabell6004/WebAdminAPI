package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsInfo {
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
    private Date availableOn;

    @JsonProperty("Labeled")
    private String labeled;
}
