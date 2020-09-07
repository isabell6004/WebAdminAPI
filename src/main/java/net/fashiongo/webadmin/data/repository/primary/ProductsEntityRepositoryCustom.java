package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.statistics.AdminServerProducts;

import java.util.List;

public interface ProductsEntityRepositoryCustom {
    Long getTotalItemCount(Integer adminWebServerID, Integer imageServerID);

    List<AdminServerProducts> getAdminServerProducts(Integer adminWebServerID, Integer imageServerID, String vendorname);
}
