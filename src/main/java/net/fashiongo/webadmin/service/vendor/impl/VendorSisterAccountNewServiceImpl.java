package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.vendor.VendorSisterAccountNewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by jinwoo on 2020-01-09.
 */
@Slf4j
@Service
public class VendorSisterAccountNewServiceImpl implements VendorSisterAccountNewService {

    @Value("${fashionGoApi.fashionGo-api.endpoint}")
    private String fashionGoApi;

    private HttpClientWrapper httpCaller;

    public VendorSisterAccountNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void regist(Integer vendorId, Integer sisterVendorId) {
        final String endpoint = fashionGoApi + "/v1.0/vendor/" + vendorId + "/sister";
        SisterAccountCommand newRequest = SisterAccountCommand.create(sisterVendorId);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader());
    }

    @Override
    public void delete(Integer vendorId, Integer sisterVendorId) {
        final String endpoint = fashionGoApi + "/v1.0/vendor/" + vendorId + "/sister/" + sisterVendorId;
        httpCaller.post(endpoint, FashionGoApiHeader.getHeader());
    }

    @Getter
    static class SisterAccountCommand {

        private Integer sisterVendorId;

        static SisterAccountCommand create(Integer sisterVendorId) {
            SisterAccountCommand command = new SisterAccountCommand();
            command.sisterVendorId = sisterVendorId;
            return command;
        }
    }

}
