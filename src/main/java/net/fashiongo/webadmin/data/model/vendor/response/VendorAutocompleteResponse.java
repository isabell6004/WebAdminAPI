package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VendorAutocompleteResponse {
    private Integer wholeSalerId;
    private String companyName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public VendorAutocompleteResponse() {
    }

    @Builder
    public VendorAutocompleteResponse(Integer wholeSalerId, String companyName, String firstName, String lastName, String email, String phone) {
        this.wholeSalerId = wholeSalerId;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public static VendorAutocompleteResponse create(VendorResponse vendor) {
        return VendorAutocompleteResponse.builder()
                .wholeSalerId(vendor.getVendorId().intValue())
                .companyName(vendor.getVendorName())
                .firstName(vendor.getFirstName())
                .lastName(vendor.getLastName())
                .email(vendor.getEmail())
                .phone(vendor.getAddresses().get(0).getPhone())
                .build();
    }

    public static List<VendorAutocompleteResponse> create(Collection<VendorResponse> vendors) {
        if (CollectionUtils.isEmpty(vendors))
            return Collections.emptyList();

        return vendors.stream().map(VendorAutocompleteResponse::create).collect(Collectors.toList());
    }
}
