package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import net.fashiongo.webadmin.service.FashionGoApiConfig;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.SisterVendorNewService;
import org.springframework.stereotype.Service;

@Service
public class SisterVendorNewServiceImpl implements SisterVendorNewService {

    private final HttpClientWrapper httpCaller;

    public SisterVendorNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void createSisterVendor(Integer vendorId, Integer sisterVendorId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/sister";
        CreateSisterVendorCommand newRequest = new CreateSisterVendorCommand(sisterVendorId.longValue());
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader());
    }

    @Override
    public void deleteSisterVendor(Integer vendorId, Integer mapId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/v1.0/sister/" + mapId;
        httpCaller.delete(endpoint, FashionGoApiHeader.getHeader());
    }

    @Getter
    private class CreateSisterVendorCommand {
        private Long sisterVendorId;

        private CreateSisterVendorCommand(Long sisterVendorId) {
            this.sisterVendorId = sisterVendorId;
        }
    }
}
