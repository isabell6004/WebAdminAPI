package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class BestItems {
    @JsonProperty(value = "RowNo")
    private Integer rowNo;

    @JsonProperty(value = "ProductID")
    private Integer productID;

    @JsonProperty(value = "TotAmt")
    private Integer totAmt;

    @JsonProperty(value = "TotQty")
    private Integer totQty;

    @JsonProperty(value = "ActivatedOn")
    private LocalDateTime activatedOn;

    @JsonProperty(value = "ProductID1")
    private Integer productID1;

    @JsonProperty(value = "ProductName")
    private String productName;

    @JsonProperty(value = "CompanyName")
    private String companyName;

    @JsonProperty(value = "UnitPrice")
    private BigDecimal unitPrice;

    @JsonProperty(value = "ImageUrlRoot")
    private String imageUrlRoot;

    @JsonProperty(value = "DirName")
    private String dirName;

    @JsonProperty(value = "PictureGeneral")
    private String pictureGeneral;
}
