package net.fashiongo.webadmin.data.model.collection.response;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CollectionProductQuickShopResponse {
    private String productName;

    private String styleNo;

    private String vendorName;

    private String vendorDirName;

    private List<String> productImages;

    private boolean isPremium;

    private boolean isRewardVendor;

    private boolean isConsolidation;

    private BigDecimal sellingPrice;

    private BigDecimal listingPrice;

    private boolean isEvenPack;

    private List<Integer> packs;

    private List<String> sizes;

    private List<Inventory> inventories;

    private int minOrderQty;

    private String latestPromotion;

    @Getter
    public static class Inventory {
        private String colorName;

        private LocalDateTime availableOn;

        private int availableQty;

        private String sizeName;
    }
}
