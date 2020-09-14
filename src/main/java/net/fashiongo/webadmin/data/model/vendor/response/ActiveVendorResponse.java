package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorSettingEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ActiveVendorResponse {
    private Integer wholesalerId;
    private String companyName;
    private Boolean active;
    private String dirName;

    public ActiveVendorResponse() {
    }

    @Builder
    public ActiveVendorResponse(Integer wholesalerId, String companyName, Boolean active, String dirName) {
        this.wholesalerId = wholesalerId;
        this.companyName = companyName;
        this.active = active;
        this.dirName = dirName;
    }

    public static ActiveVendorResponse create(VendorEntity vendor) {
        VendorSettingEntity setting = vendor.getVendorSetting().stream().findFirst().get();
        return ActiveVendorResponse.builder()
                .wholesalerId(vendor.getVendor_id().intValue())
                .companyName(vendor.getName())
                .active(setting.getStatusCode() >= 1 && setting.getStatusCode() < 4)
                .dirName(vendor.getDirname())
                .build();
    }

    public static List<ActiveVendorResponse> create(Collection<VendorEntity> vendors) {
        if (CollectionUtils.isEmpty(vendors))
            return Collections.emptyList();

        return vendors.stream().map(ActiveVendorResponse::create).collect(Collectors.toList());
    }

    public static ActiveVendorResponse createResponse(VendorResponse vendor) {
        VendorSettingSummaryResponse setting = vendor.getSetting();
        return ActiveVendorResponse.builder()
                .wholesalerId(vendor.getVendorId().intValue())
                .companyName(vendor.getVendorName())
                .active(setting.getStatusCode() >= 1 && setting.getStatusCode() < 4)
                .dirName(vendor.getDirName())
                .build();
    }

    public static List<ActiveVendorResponse> createResponse(Collection<VendorResponse> vendors) {
        if (CollectionUtils.isEmpty(vendors))
            return Collections.emptyList();

        return vendors.stream().map(ActiveVendorResponse::createResponse).collect(Collectors.toList());
    }
}
