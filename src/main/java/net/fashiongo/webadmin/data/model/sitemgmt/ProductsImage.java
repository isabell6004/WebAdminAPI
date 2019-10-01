package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsImage {
    @JsonProperty("DirName")
    private String dirName;

    @JsonProperty("UrlPath")
    private String urlPath;

    @JsonProperty("ProductImageID")
    private Integer productImageID;

    @JsonProperty("ProductID")
    private Integer productID;

    @JsonProperty("ColorID")
    private Integer colorID;

    @JsonProperty("ImageName")
    private String imageName;

    @JsonProperty("ListOrder")
    private Integer listOrder;

    @JsonProperty("Active")
    private Boolean active;
}
