package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.BannerRequestResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.primary.ListVendorImageType;

import java.util.List;

public interface BannerRequestService {

    List<ListVendorImageType> getVendorImageType();

    void setDenyBanner(SetDenyBannerParameter parameters);

    void setApproveBanner(SetDenyBannerParameter parameters);

    @Deprecated
    void setRestoreBanner(SetDenyBannerParameter parameters);

    void delBannerRequest(SetDenyBannerParameter parameters);
}
