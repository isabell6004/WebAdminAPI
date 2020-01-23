package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@NoArgsConstructor
public class BestItems {
    @JsonProperty(value = "RowNo")
    private Long rowNo;

    @JsonProperty(value = "ProductID")
    private Integer productID;

    @JsonProperty(value = "TotAmt")
    private Long totAmt;

    @JsonProperty(value = "TotQty")
    private Long totQty;

    @JsonProperty(value = "ActivatedOn")
    private LocalDateTime activatedOn;

    @JsonProperty(value = "ProductID1")
    private Integer productID1;

    @JsonProperty(value = "ProductName")
    private String productName;

    @JsonProperty(value = "CompanyName")
    private String companyName;

    @JsonProperty(value = "UnitPrice")
    private Double unitPrice;

    @JsonProperty(value = "ImageUrlRoot")
    private String imageUrlRoot;

    @JsonProperty(value = "DirName")
    private String dirName;

    @JsonProperty(value = "PictureGeneral")
    private String pictureGeneral;

    public BestItems(Long rowNo, Integer productID, Long totAmt, Long totQty, Date activatedOn, Integer productID1, String productName, String companyName, BigDecimal unitPrice, String imageUrlRoot, String dirName, String pictureGeneral) {
        this.rowNo = rowNo;
        this.productID = productID;
        this.totAmt = totAmt;
        this.totQty = totQty;
        this.activatedOn = activatedOn == null ? null : LocalDateTime.ofInstant(activatedOn.toInstant(), ZoneId.systemDefault());
        this.productID1 = productID1;
        this.productName = productName;
        this.companyName = companyName;
        this.unitPrice = unitPrice == null ? null : unitPrice.doubleValue();
        this.imageUrlRoot = imageUrlRoot;
        this.dirName = dirName;
        this.pictureGeneral = pictureGeneral;
    }
}
