package net.fashiongo.webadmin.data.model.bestofbest.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BestItemProductListResponse {
    private Integer productId;
    private String productName;
    private Long vendorId;
    private String vendorName;
    private BigDecimal sellingPrice;
    private BigDecimal listingPrice;
    private Integer status;
    private String imageUrl;

}
