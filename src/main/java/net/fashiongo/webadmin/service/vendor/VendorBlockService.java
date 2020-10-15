package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;

/**
 * Created by jinwoo on 2020-01-02.
 */
public interface VendorBlockService {

    Boolean updateVendorAdminLogin(VendorBlockAdminLoginParameter param);
    Boolean updateVendorAd(VendorBlockAdParameter param);
    Boolean updateVendorPayout(VendorBlockPayoutParameter param);
}
