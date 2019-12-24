package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.sitemgmt.ProductsSize;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsColors;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsInfo;
import net.fashiongo.webadmin.data.model.statistics.AdminServerProducts;

import java.util.List;

public interface ProductsEntityRepositoryCustom {
    List<ProductsInfo> getProductsInfo(Integer productID);

    List<ProductsColors> getProductsColors(Integer productsID);

    List<ProductsSize> getProductsSizes(Integer productsID);

    Long getTotalItemCount(Integer adminWebServerID, Integer imageServerID);

    List<AdminServerProducts> getAdminServerProducts(Integer adminWebServerID, Integer imageServerID, String vendorname);
}
