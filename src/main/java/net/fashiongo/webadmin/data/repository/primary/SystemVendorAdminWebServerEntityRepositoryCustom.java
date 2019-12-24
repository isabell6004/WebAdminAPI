package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.statistics.VendorAdminWebServerUrl;

import java.util.List;

public interface SystemVendorAdminWebServerEntityRepositoryCustom {

    List<VendorAdminWebServerUrl> findURLGroupByWebServerIDAndAdminWebServerID();

}
