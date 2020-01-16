package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.service.FashionGoApiConfig;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.VendorSisterAccountNewService;
import org.springframework.stereotype.Service;

/**
 * Created by jinwoo on 2020-01-09.
 */
@Slf4j
@Service
public class VendorSisterAccountNewServiceImpl implements VendorSisterAccountNewService {

    private HttpClientWrapper httpCaller;

    public VendorSisterAccountNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void regist(Integer vendorId, Integer sisterVendorId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/sister";
        SisterAccountCommand newRequest = new SisterAccountCommand(sisterVendorId);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader());
    }

    @Override
    public void delete(Integer vendorId, Integer sisterVendorId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/sister/" + sisterVendorId;
        httpCaller.post(endpoint, FashionGoApiHeader.getHeader());
    }

    @Getter
    private class SisterAccountCommand {

        private Integer sisterVendorId;

        private SisterAccountCommand(Integer sisterVendorId) {
            this.sisterVendorId = sisterVendorId;
        }
    }

}
