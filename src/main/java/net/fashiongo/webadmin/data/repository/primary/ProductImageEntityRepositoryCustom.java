package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.sitemgmt.ProductsImage;

import java.util.List;

public interface ProductImageEntityRepositoryCustom {
    List<ProductsImage> getProductsImage(Integer productID);
}
