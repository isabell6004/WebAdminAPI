package net.fashiongo.webadmin.data.model.sitemgmt.response.mapper;

import net.fashiongo.webadmin.data.model.sitemgmt.response.GetProductDetailResponse;
import net.fashiongo.webadmin.model.product.Product;

public class GetProductDetailResponseMapper {
    public static GetProductDetailResponse convert(Product product) {
        return net.fashiongo.webadmin.data.model.sitemgmt.response.GetProductDetailResponse.builder()
                .productId(product.getProductId())
                .styleNo(product.getStyleNo())
                .itemName(product.getItemName())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .fabricDescription(product.getFabricDescription())
                .madeIn(product.getMadeIn())
                .label(product.getLabel().getLabelTypeName())
                .stockAvailability(product.getStockAvailability())
                .availableOn(product.getAvailableOn())
                .imageUrl(product.getImageUrl())
                .images(product.getImages())
                .productMasterColors(product.getProductMasterColors())
                .sizes(product.getSize().getSizes())
                .build();
    }
}
