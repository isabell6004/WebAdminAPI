package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.entity.primary.vendor.VendorProductRow;
import net.fashiongo.webadmin.data.model.vendor.VendorProductListResponse;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorProductRepository;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenewalVendorService {

	private final VendorProductRepository vendorProductRepository;

	@Autowired
	public RenewalVendorService(VendorProductRepository vendorProductRepository) {
		this.vendorProductRepository = vendorProductRepository;
	}

	public VendorProductListResponse getProductList(GetProductListParameter parameters) {
		List<VendorProductRow> vendorProducts = vendorProductRepository.getVendorProducts(parameters.getWholesalerid(), parameters.getVendorcategoryid(), parameters.getProductname());
		return VendorProductListResponse.builder()
				.products(vendorProducts)
				.build();
	}

	public List<ProductColorRow> getProductColor(Integer productId) {
		return vendorProductRepository.getColors(productId);
	}
}
