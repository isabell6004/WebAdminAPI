package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.entity.primary.vendor.VendorProductRow;

import java.util.List;

public interface VendorProductRepository {
	List<VendorProductRow> getVendorProducts(int wholesalerId, Integer vendorCategoryId, String productName);
	List<ProductColorRow> getColors(int productId);
}
