package net.fashiongo.webadmin.data.model.sitemgmt.response;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.model.product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetProductDetailResponse {
    private final int productId;

    private final String styleNo;

    private final String itemName;

    private final String description;

    private final BigDecimal unitPrice;

    private final String fabricDescription;

    private final String madeIn;

    private final String label;

    private final String stockAvailability;

    private final LocalDateTime availableOn;

    private final String imageUrl;

    private final List<Product.Image> images;

    private final String productMasterColors;

    private final String sizes;

    @Builder
    public GetProductDetailResponse(int productId,
                                    String styleNo,
                                    String itemName,
                                    String description,
                                    BigDecimal unitPrice,
                                    String fabricDescription,
                                    String madeIn,
                                    String label,
                                    String stockAvailability,
                                    LocalDateTime availableOn,
                                    String imageUrl,
                                    List<Product.Image> images,
                                    String productMasterColors,
                                    String sizes) {
        this.productId = productId;
        this.styleNo = styleNo;
        this.itemName = itemName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.fabricDescription = fabricDescription;
        this.madeIn = madeIn;
        this.label = label;
        this.stockAvailability = stockAvailability;
        this.availableOn = availableOn;
        this.imageUrl = imageUrl;
        this.images = images;
        this.productMasterColors = productMasterColors;
        this.sizes = sizes;
    }
}
