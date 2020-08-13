package net.fashiongo.webadmin.data.model.collection.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CollectionProductResponse {
    private LocalDateTime activatedOn;

    private LocalDateTime availableOn;

    private String categoryName1;

    private String categoryName2;

    private String categoryName3;

    private String companyName;

    private String dirName;

    private String imageUrlRoot;

    private char isRewardVendor;

    private float price;

    private String pictureGeneral;

    private long productId;

    private String productMasterColors;

    private String productName;

    private int stockAvailability;

    private float unitPrice1;

    private int vendorClassCode;

    private long wholeSalerId;

    private int typeCode;
}
