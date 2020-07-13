package net.fashiongo.webadmin.model.ads.response;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FetchTotalAmountResponse {
    private int vendorId;
    private int purchaseCount;
    private BigDecimal purchaseAmount;
}
