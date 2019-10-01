package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsSelectCheck;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsSize;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsColors;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsImage;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsInfo;

import java.util.List;

@Getter
@Builder
public class GetProductDetailResponse {
    @JsonProperty("Table")
    private List<ProductsInfo> productInfolist;

    @JsonProperty("Table1")
    private List<ProductsImage> productImagelist;

    @JsonProperty("Table2")
    private List<ProductsColors> productColorslist;

    @JsonProperty("Table3")
    private List<ProductsSize> productSizelist;

    @JsonProperty("Table4")
    private ProductsSelectCheck productSelectChecklist;
}
