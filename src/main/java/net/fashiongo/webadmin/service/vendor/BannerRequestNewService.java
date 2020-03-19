package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.BannerRequestResponse;
import net.fashiongo.webadmin.data.model.vendor.SetVendorImageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import org.springframework.scheduling.annotation.Async;

public interface BannerRequestNewService {
    @Async("fashionGoApiThreadPoolTaskExecutor")
    void reject(Integer vendorId, SetDenyBannerParameter request, Integer requestUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void delete(Integer vendorId, Integer bannerId, Integer requestedUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void insert(Integer imageOriginalId, SetVendorImageParameter request, Integer requestedUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void approve(Integer vendorId, Integer bannerId, Integer requestedUserId, String requestUserName);

    BannerRequestResponse get(GetBannerRequestParameter parameters);
}
