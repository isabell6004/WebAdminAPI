package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.FashionGoFormEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.entity.primary.vendor.VendorProductRow;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.repository.primary.form.FashionGoFormEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.form.FormOrderingType;
import net.fashiongo.webadmin.data.repository.primary.vendor.*;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenewalVendorService {

	private final VendorProductRepository vendorProductRepository;
	private final VendorImageRequestEntityRepository vendorImageRequestEntityRepository;
	private final FashionGoFormEntityRepository fashionGoFormEntityRepository;

	@Autowired
	public RenewalVendorService(VendorProductRepository vendorProductRepository,
								VendorImageRequestEntityRepository vendorImageRequestEntityRepository,
								FashionGoFormEntityRepository fashionGoFormEntityRepository) {
		this.vendorProductRepository = vendorProductRepository;
		this.vendorImageRequestEntityRepository = vendorImageRequestEntityRepository;
		this.fashionGoFormEntityRepository = fashionGoFormEntityRepository;
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

	public BannerRequestResponse getBannerRequest(GetBannerRequestParameter parameters) {
		VendorImageRequestSelectParameter parameter = VendorImageRequestSelectParameter.builder()
				.pageNumber(parameters.getPageNum())
				.pageSize(parameters.getPageSize())
				.wholesalerId(null)
				.wholesalerName(parameters.getSearchKeyword())
				.vendorImageTypeId(parameters.getSearchType())
				.approvalType(VendorImageRequestApprovalType.getType(parameters.getSearchStatus()))
				.active(null)
				.searchFrom(parameters.getFromDate() != null ? LocalDateTime.parse(parameters.getFromDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")) : null)
				.searchTo(parameters.getToDate() != null ? LocalDateTime.parse(parameters.getToDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")) : null)
				.orderingType(VendorImageRequestOrderingType.getType(parameters.getOrderby()))
				.showDeleted(null)
				.build();
		Page<VendorImageRequestEntity> result = vendorImageRequestEntityRepository.getVendorImageRequests(parameter);

		return BannerRequestResponse.builder()
				.bannerImageList(result.getContent().stream().map(VendorImageRequestResponse::convert).collect(Collectors.toList()))
				.total(Collections.singletonList(BannerRequestCount.builder().count(result.getTotalElements()).build()))
				.build();

	}

	public VendorFormListResponse getVendorFormsList(GetVendorFormsListParameter parameters) {
		// In reality, it is sorted in the front-end app.
		// FormOrderingType.getFromStringValue(parameters.getOrderBy())
		List<FashionGoFormEntity> forms = fashionGoFormEntityRepository.getForms(FormOrderingType.FASHION_GO_FORM_ID_DESC);
		return VendorFormListResponse.builder()
				.fashionGoFormList(forms.stream().map(VendorFormResponse::convert).collect(Collectors.toList()))
				.build();
	}
}
