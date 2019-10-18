package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.FashionGoFormEntity;
import net.fashiongo.webadmin.data.entity.primary.SecurityUserEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorContractDocumentEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.entity.primary.vendor.VendorProductRow;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse;
import net.fashiongo.webadmin.data.repository.primary.*;
import net.fashiongo.webadmin.data.repository.primary.form.FashionGoFormEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.form.FormOrderingType;
import net.fashiongo.webadmin.data.repository.primary.vendor.*;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RenewalVendorService {

	private final VendorProductRepository vendorProductRepository;
	private final VendorImageRequestEntityRepository vendorImageRequestEntityRepository;
	private final FashionGoFormEntityRepository fashionGoFormEntityRepository;
	private final VendorContractDocumentEntityRepository vendorContractDocumentEntityRepository;
	private final WholeSalerEntityRepository wholeSalerEntityRepository;
	private final VendorContractEntityRepository vendorContractEntityRepository;
	private final SecurityUserEntityRepository securityUserEntityRepository;
	private final CodeWholeSalerCompanyTypeEntityRepository codeWholeSalerCompanyTypeEntityRepository;
	private final CodeCountryEntityRepository codeCountryEntityRepository;

	@Autowired
	public RenewalVendorService(VendorProductRepository vendorProductRepository,
	                            VendorImageRequestEntityRepository vendorImageRequestEntityRepository,
	                            FashionGoFormEntityRepository fashionGoFormEntityRepository, VendorContractDocumentEntityRepository vendorContractDocumentEntityRepository, WholeSalerEntityRepository wholeSalerEntityRepository, VendorContractEntityRepository vendorContractEntityRepository, SecurityUserEntityRepository securityUserEntityRepository, CodeWholeSalerCompanyTypeEntityRepository codeWholeSalerCompanyTypeEntityRepository, CodeCountryEntityRepository codeCountryEntityRepository) {
		this.vendorProductRepository = vendorProductRepository;
		this.vendorImageRequestEntityRepository = vendorImageRequestEntityRepository;
		this.fashionGoFormEntityRepository = fashionGoFormEntityRepository;
		this.vendorContractDocumentEntityRepository = vendorContractDocumentEntityRepository;
		this.wholeSalerEntityRepository = wholeSalerEntityRepository;
		this.vendorContractEntityRepository = vendorContractEntityRepository;
		this.securityUserEntityRepository = securityUserEntityRepository;
		this.codeWholeSalerCompanyTypeEntityRepository = codeWholeSalerCompanyTypeEntityRepository;
		this.codeCountryEntityRepository = codeCountryEntityRepository;
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

	public GetVendorContractDocumentHistoryResponse getVendorContractDocumentHistory(Integer vendorContractID) {

		List<VendorContractDocumentEntity> vendorContractDocumentEntities = vendorContractDocumentEntityRepository.findAllByVendorContractID(vendorContractID);

		return GetVendorContractDocumentHistoryResponse.builder()
				.vendorContractDocumentHistoryList(
						vendorContractDocumentEntities.stream()
						.map(vendorContractDocumentEntity ->
								VendorContractDocumentHistory.builder()
										.vendorContractID(vendorContractDocumentEntity.getVendorContractID())
										.vendorContractDocumentID(vendorContractDocumentEntity.getVendorContractDocumentID())
										.documentTypeID(vendorContractDocumentEntity.getDocumentTypeID())
										.createdBy(vendorContractDocumentEntity.getCreatedBy())
										.createdOn(vendorContractDocumentEntity.getCreatedOn().toLocalDateTime())
										.note(vendorContractDocumentEntity.getNote())
										.receivedBy(vendorContractDocumentEntity.getReceivedBy())
										.fileName(vendorContractDocumentEntity.getFileName())
										.fileName2(vendorContractDocumentEntity.getFileName2())
										.fileName3(vendorContractDocumentEntity.getFileName3())
										.checkBox(false)
								.build()
						).collect(Collectors.toList())
				)
				.build();
	}

	public GetVendorDetailInfoDataResponse getVendorDetailInfoData(Integer wholeSalerID) {

		VendorDetailDate vendorDetailDate = wholeSalerEntityRepository.findById(wholeSalerID)
				.map(simpleWholeSalerEntity -> VendorDetailDate.builder()
						.actualOpenDate(Optional.ofNullable(simpleWholeSalerEntity.getActualOpenDate()).map(Timestamp::toLocalDateTime).orElse(null))
						.lastModifiedDateTime(Optional.ofNullable(simpleWholeSalerEntity.getLastModifiedDateTime()).map(Timestamp::toLocalDateTime).orElse(null))
						.wholeSalerId(simpleWholeSalerEntity.getWholeSalerId())
						.lastUser(simpleWholeSalerEntity.getLastUser())
						.userID(simpleWholeSalerEntity.getUserID())
						.build())
				.orElse(null);

		String securityUserName = vendorContractEntityRepository.findAllByWholeSalerId(wholeSalerID)
				.stream()
				.findFirst()
				.map(vendorContractEntity -> {
						return vendorContractEntity.getRepID() != null ? securityUserEntityRepository.findById(vendorContractEntity.getRepID())
								.map(SecurityUserEntity::getUserName)
								.orElse(null) : null;
						}
				).orElse(null);

		List<VendorCompanyType> vendorCompanyTypeList = codeWholeSalerCompanyTypeEntityRepository.findAllByActive(true)
				.stream()
				.map(entity -> VendorCompanyType.builder()
						.active(entity.isActive())
						.companyTypeID(entity.getCompanyTypeID())
						.companyTypeName(entity.getCompanyTypeName())
						.build()
				).collect(Collectors.toList());

		List<Country> countryList = codeCountryEntityRepository.findAllByActive(true)
				.stream()
				.map(entity -> Country.builder()
						.countryName(entity.getCountryName())
						.build()
				).collect(Collectors.toList());

		return GetVendorDetailInfoDataResponse.builder()
				.sessionUsrID(Utility.getUsername())
				.vendorDefaultInfo(vendorDetailDate)
				.fgAeName(securityUserName)
				.vendorCompanyTypeList(vendorCompanyTypeList)
				.countryList(countryList)
				.build();
	}
}
