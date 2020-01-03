package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.primary.ListVendorImageType;

import java.util.List;

public interface BannerRequestService {
    List<ListVendorImageType> getVendorImageType();

    void setDenyBanner(SetDenyBannerParameter parameters);

    void setApproveBanner(SetDenyBannerParameter parameters);

    void setRestoreBanner(SetDenyBannerParameter parameters);

    void delBannerRequest(SetDenyBannerParameter parameters);
}
