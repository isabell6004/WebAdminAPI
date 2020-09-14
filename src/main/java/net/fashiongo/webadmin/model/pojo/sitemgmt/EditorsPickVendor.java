package net.fashiongo.webadmin.model.pojo.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EditorsPickVendor {
    @JsonProperty("WholeSalerID")
    private Integer wholeSalerId;

    @JsonProperty("CompanyName")
    private String companyName;

    private String phone;

    public EditorsPickVendor() {
    }

    @Builder
    public EditorsPickVendor(Integer wholeSalerId, String companyName, String phone) {
        this.wholeSalerId = wholeSalerId;
        this.companyName = companyName;
        this.phone = phone;
    }

    public static EditorsPickVendor create(VendorEntity vendor) {
        return EditorsPickVendor.builder()
                .wholeSalerId(vendor.getVendor_id().intValue())
                .companyName(vendor.getName())
                .phone(vendor.getVendorAddresses().stream().findFirst().orElse(null).getPhone())
                .build();
    }

    public static List<EditorsPickVendor> create(Collection<VendorEntity> vendors) {
        if (CollectionUtils.isEmpty(vendors))
            return Collections.EMPTY_LIST;

        return vendors.stream().map(EditorsPickVendor::create).collect(Collectors.toList());
    }
}
