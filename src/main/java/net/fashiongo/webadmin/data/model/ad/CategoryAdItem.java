package net.fashiongo.webadmin.data.model.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryAdItem {
    @JsonProperty("MapID")
    private Integer mapID;

    @JsonProperty("ProductID")
    private Integer productID;

    @JsonProperty("ListOrder")
    private Integer listOrder;

    @JsonProperty("WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty("CompanyName")
    private String companyName;

    @JsonProperty("PictureGeneral")
    private String pictureGeneral;

    @JsonProperty("DirName")
    private String dirName;

    @JsonProperty("ProductName")
    private String productName;

    @JsonProperty("ImageUrlRoot")
    private String imageUrlRoot;
}
