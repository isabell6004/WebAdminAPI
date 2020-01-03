package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import org.springframework.scheduling.annotation.Async;

public interface BannerRequestNewService {
    @Async
    void approveBanner(SetDenyBannerParameter request);

    @Async
    void rejectBanner(SetDenyBannerParameter request);

    @Async
    void deleteBanner(SetDenyBannerParameter request);
}
