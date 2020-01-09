package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import org.springframework.scheduling.annotation.Async;

public interface BannerRequestNewService {
    @Async
    void approveBanner(Integer vendorId, SetDenyBannerParameter request);

    @Async
    void rejectBanner(Integer vendorId, SetDenyBannerParameter request);

    @Async
    void deleteBanner(Integer vendorId, SetDenyBannerParameter request);
}
