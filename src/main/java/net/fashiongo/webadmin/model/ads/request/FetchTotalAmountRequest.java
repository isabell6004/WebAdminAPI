package net.fashiongo.webadmin.model.ads.request;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FetchTotalAmountRequest {
    private List<Integer> vendorIds;
    private LocalDate beginIssuedOn;
    private LocalDate endIssuedOn;

    public static FetchTotalAmountRequest of(List<Integer> vendorIds, LocalDate beginIssuedOn, LocalDate endIssuedOn) {
        FetchTotalAmountRequest request = new FetchTotalAmountRequest();
        request.vendorIds = new ArrayList<>(vendorIds);
        request.beginIssuedOn = beginIssuedOn;
        request.endIssuedOn = endIssuedOn;
        return request;
    }
}
