package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.vendor.SisterVendorNewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SisterVendorNewServiceImpl implements SisterVendorNewService {

    @Value("${fashionGoApi.fashionGo-api.endpoint}")
    private String fashionGoApi;

    private final HttpClientWrapper httpCaller;

    public SisterVendorNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void createSisterVendor(Integer vendorId, Integer sisterVendorId) {
        final String endpoint = fashionGoApi + "/v1.0/vendor/" + vendorId + "/sister";
        CreateSisterVendorCommand newRequest = new CreateSisterVendorCommand(sisterVendorId.longValue());
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(vendorId.toString()));
    }

    @Override
    public void deleteSisterVendor(Integer vendorId, Integer mapId) {
        final String endpoint = fashionGoApi + "/v1.0/vendor/" + vendorId + "/v1.0/sister/" + mapId;
        httpCaller.delete(endpoint, FashionGoApiHeader.getHeader(vendorId.toString()));
    }

    @Getter
    private class CreateSisterVendorCommand {
        private Long sisterVendorId;

        private CreateSisterVendorCommand(Long sisterVendorId) {
            this.sisterVendorId = sisterVendorId;
        }
    }
}
