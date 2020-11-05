package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderActiveVendorResponse {
    private Integer wholesalerId;
    private String companyName;

    public OrderActiveVendorResponse() {
    }

    @Builder
    public OrderActiveVendorResponse(Integer wholesalerId, String companyName) {
        this.wholesalerId = wholesalerId;
        this.companyName = companyName;
    }

    public static OrderActiveVendorResponse createResponse(VendorResponse vendor) {

        return OrderActiveVendorResponse.builder()
                .wholesalerId(vendor.getVendorId().intValue())
                .companyName(vendor.getVendorName())
                .build();
    }

    public static List<OrderActiveVendorResponse> createResponse(Collection<VendorResponse> vendors) {
        if (CollectionUtils.isEmpty(vendors))
            return Collections.emptyList();

        return vendors.stream()
                .filter(s -> s.getSetting().getStatusCode().equals(3))
                .map(OrderActiveVendorResponse::createResponse)
                .collect(Collectors.toList());
    }
}
