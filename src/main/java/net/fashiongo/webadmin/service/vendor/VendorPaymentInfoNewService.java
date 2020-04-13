package net.fashiongo.webadmin.service.vendor;

import org.springframework.scheduling.annotation.Async;

public interface VendorPaymentInfoNewService {

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void switchPaymentMethodId(Integer wholeSalerID, Integer oldPaymentMethodId, Integer newPaymentMethodId, Integer requestUserId, String requestUserName);
}
