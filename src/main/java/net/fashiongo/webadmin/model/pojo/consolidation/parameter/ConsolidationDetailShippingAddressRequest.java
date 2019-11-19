package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsolidationDetailShippingAddressRequest {
    private int id;
    private String streetNo;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Integer countryId;
    private String isCommercialAddress;

    public boolean isCommercialAddress() {
        return isCommercialAddress.equals("true");
    }
}
