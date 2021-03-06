package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.SisterVendorNewService;
import org.springframework.stereotype.Service;

@Service
public class SisterVendorNewServiceImpl implements SisterVendorNewService {

    private final HttpClientWrapper httpCaller;

    public SisterVendorNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void createSisterVendor(Integer vendorId, Integer originalId, Integer sisterVendorId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/sister-vendors";
        CreateSisterVendorCommand newRequest = new CreateSisterVendorCommand(originalId.longValue(), sisterVendorId.longValue());
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public void deleteSisterVendor(Integer vendorId, Integer mapId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/sister-vendors/" + mapId;
        httpCaller.delete(endpoint, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Getter
    private class CreateSisterVendorCommand {

        private Long id;

        private Long sisterVendorId;

        private CreateSisterVendorCommand(Long id, Long sisterVendorId) {
            this.id = id;
            this.sisterVendorId = sisterVendorId;
        }
    }
}
