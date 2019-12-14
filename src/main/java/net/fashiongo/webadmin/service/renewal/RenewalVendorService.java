package net.fashiongo.webadmin.service.renewal;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.dao.primary.VendorAdminAccountRepository;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.entity.primary.vendor.VendorProductRow;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.vendor.VendorAdminAccount;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.response.GetAssignedUserListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorAdminAccountLogListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorBasicInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorGroupingResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorListCSVResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorSettingResponse;
import net.fashiongo.webadmin.data.repository.primary.*;
import net.fashiongo.webadmin.data.repository.primary.form.FashionGoFormEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.form.FormOrderingType;
import net.fashiongo.webadmin.data.repository.primary.vendor.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import net.fashiongo.webadmin.service.ApiService;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RenewalVendorService extends ApiService {

	@Autowired
	private CacheService cacheService;

	private final VendorProductRepository vendorProductRepository;
	private final VendorImageRequestEntityRepository vendorImageRequestEntityRepository;
	private final FashionGoFormEntityRepository fashionGoFormEntityRepository;
	private final VendorContractDocumentEntityRepository vendorContractDocumentEntityRepository;
	private final WholeSalerEntityRepository wholeSalerEntityRepository;
	private final VendorContractEntityRepository vendorContractEntityRepository;
	private final SecurityUserEntityRepository securityUserEntityRepository;
	private final CodeWholeSalerCompanyTypeEntityRepository codeWholeSalerCompanyTypeEntityRepository;
	private final CodeCountryEntityRepository codeCountryEntityRepository;
	private final MapWholeSalerPaymentMethodEntityRepository mapWholeSalerPaymentMethodEntityRepository;
	private final WholeShipMethodEntityRepository wholeShipMethodEntityRepository;
	private final VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;
	private final VendorNameHistoryLogEntityRepository vendorNameHistoryLogEntityRepository;
	private final ListSocialMediaEntityRepository listSocialMediaEntityRepository;
	private final VendorPayoutInfoEntityRepository vendorPayoutInfoEntityRepository;
	private final ListVendorDocumentTypeEntityRepository listVendorDocumentTypeEntityRepository;
	private final CodeVendorIndustryEntityRepository codeVendorIndustryEntityRepository;
	private final AspnetUsersEntityRepository aspnetUsersEntityRepository;
	private final AspnetMembershipEntityRepository aspnetMembershipEntityRepository;
	private final VendorAdminAccountEntityRepository vendorAdminAccountEntityRepository;
	private final VendorDirNameChangeLogEntityRepository vendorDirNameChangeLogEntityRepository;
	private final EntityActionLogEntityRepository entityActionLogEntityRepository;
	private final TodayDealEntityRepository todayDealEntityRepository;
	private final AdVendorItemEntityRepository adVendorItemEntityRepository;
	private final CustomerSocialMediaEntityRepository customerSocialMediaEntityRepository;
	private final LogCommunicationEntityRepository logCommunicationEntityRepository;
	private final VendorCapEntityRepository vendorCapEntityRepository;
	private final CodeVendorCapTypeEntityRepository codeVendorCapTypeEntityRepository;
	private final VendorBlockedEntityRepository vendorBlockedEntityRepository;
	private final ListVendorBlockReasonEntityRepository listVendorBlockReasonEntityRepository;
	private final MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository;
	private final LogVendorHoldEntityRepository logVendorHoldEntityRepository;
	private final VendorAdminLoginLogEntityRepository vendorAdminLoginLogEntityRepository;
	private final MapWholeSalerGroupEntityRepository mapWholeSalerGroupEntityRepository;
	private final RetailerRatingEntityRepository retailerRatingEntityRepository;

	@Autowired
	public RenewalVendorService(VendorProductRepository vendorProductRepository,
								VendorImageRequestEntityRepository vendorImageRequestEntityRepository,
								FashionGoFormEntityRepository fashionGoFormEntityRepository, VendorContractDocumentEntityRepository vendorContractDocumentEntityRepository, WholeSalerEntityRepository wholeSalerEntityRepository, VendorContractEntityRepository vendorContractEntityRepository, SecurityUserEntityRepository securityUserEntityRepository, CodeWholeSalerCompanyTypeEntityRepository codeWholeSalerCompanyTypeEntityRepository, CodeCountryEntityRepository codeCountryEntityRepository, MapWholeSalerPaymentMethodEntityRepository mapWholeSalerPaymentMethodEntityRepository, WholeShipMethodEntityRepository wholeShipMethodEntityRepository, VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository, VendorNameHistoryLogEntityRepository vendorNameHistoryLogEntityRepository, ListSocialMediaEntityRepository listSocialMediaEntityRepository, VendorPayoutInfoEntityRepository vendorPayoutInfoEntityRepository, ListVendorDocumentTypeEntityRepository listVendorDocumentTypeEntityRepository, CodeVendorIndustryEntityRepository codeVendorIndustryEntityRepository, AspnetUsersEntityRepository aspnetUsersEntityRepository, AspnetMembershipEntityRepository aspnetMembershipEntityRepository, VendorAdminAccountRepository vendorAdminAccountRepository, VendorAdminAccountEntityRepository vendorAdminAccountEntityRepository, VendorDirNameChangeLogEntityRepository vendorDirNameChangeLogEntityRepository, EntityActionLogEntityRepository entityActionLogEntityRepository, TodayDealEntityRepository todayDealEntityRepository, AdVendorItemEntityRepository adVendorItemEntityRepository, CustomerSocialMediaEntityRepository customerSocialMediaEntityRepository, LogCommunicationEntityRepository logCommunicationEntityRepository, VendorCapEntityRepository vendorCapEntityRepository, CodeVendorCapTypeEntityRepository codeVendorCapTypeEntityRepository, VendorBlockedEntityRepository vendorBlockedEntityRepository, ListVendorBlockReasonEntityRepository listVendorBlockReasonEntityRepository, MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository, LogVendorHoldEntityRepository logVendorHoldEntityRepository, VendorAdminLoginLogEntityRepository vendorAdminLoginLogEntityRepository, MapWholeSalerGroupEntityRepository mapWholeSalerGroupEntityRepository, RetailerRatingEntityRepository retailerRatingEntityRepository) {
		this.vendorProductRepository = vendorProductRepository;
		this.vendorImageRequestEntityRepository = vendorImageRequestEntityRepository;
		this.fashionGoFormEntityRepository = fashionGoFormEntityRepository;
		this.vendorContractDocumentEntityRepository = vendorContractDocumentEntityRepository;
		this.wholeSalerEntityRepository = wholeSalerEntityRepository;
		this.vendorContractEntityRepository = vendorContractEntityRepository;
		this.securityUserEntityRepository = securityUserEntityRepository;
		this.codeWholeSalerCompanyTypeEntityRepository = codeWholeSalerCompanyTypeEntityRepository;
		this.codeCountryEntityRepository = codeCountryEntityRepository;
		this.mapWholeSalerPaymentMethodEntityRepository = mapWholeSalerPaymentMethodEntityRepository;
		this.wholeShipMethodEntityRepository = wholeShipMethodEntityRepository;
		this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
		this.vendorNameHistoryLogEntityRepository = vendorNameHistoryLogEntityRepository;
        this.listSocialMediaEntityRepository = listSocialMediaEntityRepository;
        this.vendorPayoutInfoEntityRepository = vendorPayoutInfoEntityRepository;
		this.listVendorDocumentTypeEntityRepository = listVendorDocumentTypeEntityRepository;
		this.codeVendorIndustryEntityRepository = codeVendorIndustryEntityRepository;
		this.aspnetUsersEntityRepository = aspnetUsersEntityRepository;
		this.aspnetMembershipEntityRepository = aspnetMembershipEntityRepository;
		this.vendorAdminAccountEntityRepository = vendorAdminAccountEntityRepository;
		this.vendorDirNameChangeLogEntityRepository = vendorDirNameChangeLogEntityRepository;
		this.entityActionLogEntityRepository = entityActionLogEntityRepository;
		this.todayDealEntityRepository = todayDealEntityRepository;
		this.adVendorItemEntityRepository = adVendorItemEntityRepository;
		this.customerSocialMediaEntityRepository = customerSocialMediaEntityRepository;
		this.logCommunicationEntityRepository = logCommunicationEntityRepository;
		this.vendorCapEntityRepository = vendorCapEntityRepository;
		this.codeVendorCapTypeEntityRepository = codeVendorCapTypeEntityRepository;
		this.vendorBlockedEntityRepository = vendorBlockedEntityRepository;
		this.listVendorBlockReasonEntityRepository = listVendorBlockReasonEntityRepository;
		this.mapWholeSalerSisterEntityRepository = mapWholeSalerSisterEntityRepository;
		this.logVendorHoldEntityRepository = logVendorHoldEntityRepository;
		this.vendorAdminLoginLogEntityRepository = vendorAdminLoginLogEntityRepository;
		this.mapWholeSalerGroupEntityRepository = mapWholeSalerGroupEntityRepository;
		this.retailerRatingEntityRepository = retailerRatingEntityRepository;
	}

	@Autowired
	private ConfigurableEnvironment env;

	private List<String> getActiveProfiles() {
		return Arrays.asList(env.getActiveProfiles());
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

	public List<GetVwPaymentMethodsForVendor> getVwPaymentMethodsForVendor(GetVwPaymentMethodsForVendorParameter parameter) {

		Integer wholesalerId = parameter.getWholesalerId();
		if(wholesalerId == null) {
			wholesalerId = 0;
		}

		List<MapWholeSalerPaymentMethodEntity> mapWholeSalerPaymentMethodEntities = mapWholeSalerPaymentMethodEntityRepository.findAllByWholeSalerIdWithCodePayment(wholesalerId);

		return mapWholeSalerPaymentMethodEntities.stream()
				.map(entity -> GetVwPaymentMethodsForVendor.builder()
						.mapID(entity.getMapID())
						.paymentMethodID(entity.getPaymentMethodID())
						.wholeSalerID(entity.getWholeSalerID())
						.paymentMethodName(entity.getCodePaymentMethod() != null ? entity.getCodePaymentMethod().getPaymentMethodName() : null)
						.build()
				).collect(Collectors.toList());
	}

	public List<GetVwShipMethodsForVendor> getVwShipMethodsForVendor(GetVwShipMethodsForVendorParameter parameter) {
		Integer wholesalerId = parameter.getWholesalerId();
		if(wholesalerId == null) {
			wholesalerId = 0;
		}

		List<WholeShipMethodEntity> wholeShipMethodEntities = wholeShipMethodEntityRepository.findAllByWholeSalerIdWithShipMethod(wholesalerId);

		return wholeShipMethodEntities.stream()
				.map(entity -> GetVwShipMethodsForVendor.builder()
						.isDefault(entity.isDefault())
						.shipMethodID(entity.getShipMethodID())
						.wholeSalerID(entity.getWholeSalerID())
						.shipMethodName(entity.getShipMethod() != null ? entity.getShipMethod().getShipMethodName() : null)
						.wholeShipID(entity.getWholeShipID())
						.build()
				).collect(Collectors.toList());
	}

	public List<Vendor> getVendorListAll() {
		return wholeSalerEntityRepository.findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
	}

	public List<ListVendorDocumentTypeEntity> getListVendorDocumentType() {
		return listVendorDocumentTypeEntityRepository.findAllListVendorDocumentTypeEntity();
	}

	public List<CodeVendorIndustryEntity> getCodeVendorIndustryEntity() {
		return codeVendorIndustryEntityRepository.findAllCodeVendorIndustriesOrderById();
	}

	public GetVendorBasicInfoResponse getVendorDetailInfo(Integer wholesalerID) {
		return GetVendorBasicInfoResponse.builder()
				.vendorDetailInfoList(vendorWholeSalerEntityRepository.findAllByID(wholesalerID))
				.vendorNameHistoryLogList(vendorNameHistoryLogEntityRepository.findAllByIDOrderByCreatedOn(wholesalerID))
				.listSocialMediaList(listSocialMediaEntityRepository.findSocialMediaByWholeSalerID(wholesalerID))
				.vendorPayoutInfoList(vendorPayoutInfoEntityRepository.findAllByWholeSalerID(wholesalerID))
				.build();
	}

	public List<VendorImage> getVendorImage(Integer wid) {
		return vendorImageRequestEntityRepository.findByWholeSalerID(wid);
	}

	@Transactional
	public Integer setVendorBasicInfo(VendorDetailInfo r, Integer saveType, Integer payoutSchedule, Integer payoutScheduleWM, Integer maxPayoutPerDay, Integer payoutCount) {
		Integer result;
		String sessionUsrId = Utility.getUsername();

		try {
			WholeSalerEntity wholeSaler = vendorWholeSalerEntityRepository.findOneByID(r.getWholeSalerID());
			if (saveType == 1) {
				try {
					if (!wholeSaler.getUserId().equals(r.getUserId())) {
						AspnetUsersEntity aspUserDuplicateCheck = aspnetUsersEntityRepository.findOneByUserNameAndWholeSalerGUID(r.getUserId(), wholeSaler.getWholeSalerGUID());

						if (aspUserDuplicateCheck == null) {
							AspnetUsersEntity aspUser = aspnetUsersEntityRepository.findOneByWholeSalerGUID(wholeSaler.getWholeSalerGUID());
							aspUser.setUserName(r.getUserId());
							aspUser.setLoweredUserName(r.getUserId().toLowerCase());
							aspnetUsersEntityRepository.save(aspUser);
						} else {
							return 97;
						}
					}
				} catch (Exception ex) {
					return 98;
				}
			}

			if (StringUtils.isNotEmpty(wholeSaler.getDirName()) && StringUtils.isNotEmpty(r.getDirName())) {
				if (!wholeSaler.getDirName().equals(r.getDirName())) {
					JsonResponse retVal = cacheService.GetRedisCacheEvict_ChangeDirName(wholeSaler.getDirName(), r.getDirName());

					if(!retVal.isSuccess() && this.getActiveProfiles().contains("toast")) {
						return -1;
					}

					setDirCompanyNameChangeHistory(wholeSaler.getWholeSalerID(), wholeSaler.getDirName(), r.getDirName(), r.getCompanyName(), r.getCompanyName());
				}
			}

			wholeSaler.setFirstName(r.getFirstName());
			wholeSaler.setLastName(r.getLastName());
			wholeSaler.setCompanyName(r.getCompanyName());

			wholeSaler.setUserId(r.getUserId());
			wholeSaler.setRegCompanyName(r.getRegCompanyName());
			wholeSaler.setEmail(r.getEmail());
			wholeSaler.setCompanyTypeID(r.getCompanyTypeID());
			wholeSaler.setBusinessCategory(r.getBusinessCategory());
			wholeSaler.setEstablishedYear(r.getEstablishedYear());
			wholeSaler.setWebSite(r.getWebSite());
			wholeSaler.setDescription(r.getDescription());
			wholeSaler.setBillStreetNo(r.getBillStreetNo());
			wholeSaler.setBillStreetNo2(r.getBillStreetNo2());
			wholeSaler.setBillCity(r.getBillCity());
			wholeSaler.setBillState(r.getBillState());
			wholeSaler.setBillZipcode(r.getBillZipcode());
			wholeSaler.setCountry(r.getCountry());
			wholeSaler.setBillPhone(r.getBillPhone());
			wholeSaler.setBillFax(r.getBillFax());
			wholeSaler.setStreetNo(r.getStreetNo());
			wholeSaler.setStreetNo2(r.getStreetNo2());

			wholeSaler.setCity(r.getCity());
			wholeSaler.setState(r.getState());
			wholeSaler.setZipcode(r.getZipcode());
			wholeSaler.setCountry(r.getCountry());
			wholeSaler.setPhone(r.getPhone());
			wholeSaler.setFax(r.getFax());

			if (saveType == 2) {
				if (r.getOrderActive() != wholeSaler.getOrderActive() || r.getShopActive() != wholeSaler.getShopActive() || r.getActive() != wholeSaler.getActive()) {
					String logDetail = "Active = " + r.getActive() + ",ShopActive = " + r.getShopActive() + ",OrderActive = " + r.getOrderActive();
					setEntityActionLogDetail(1, r.getWholeSalerID(), 3003, logDetail);
				} if(wholeSaler.getTransactionFeeRate1() != r.getTransactionFeeRate1() || wholeSaler.getTransactionFeeRate2() != r.getTransactionFeeRate2()
						|| wholeSaler.getTransactionFeeRate1Intl() != r.getTransactionFeeRate1Intl() || wholeSaler.getTransactionFeeRate2Intl() != r.getTransactionFeeRate2Intl()
						|| wholeSaler.getTransactionFeeFixed() != r.getTransactionFeeFixed() || wholeSaler.getCommissionRate() != r.getCommissionRate()) {
					String logDetail = "TransactionFeeRate1 = " + r.getTransactionFeeRate1() + ",TransactionFeeRate2 = " + r.getTransactionFeeRate2() +
							",TransactionFeeRate1Intl = " + r.getTransactionFeeRate1Intl() + ",TransactionFeeRate2Intl = " + r.getTransactionFeeRate2Intl() +
							",TransactionFeeFixed = " + r.getTransactionFeeFixed() + ",CommissionRate = " + r.getCommissionRate();
					setEntityActionLogDetail(1, r.getWholeSalerID(), 3004, logDetail);
				} if (wholeSaler.getShopActive() != r.getShopActive() && r.getShopActive()) {
					AspnetMembershipEntity membership = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(wholeSaler.getWholeSalerGUID());
					membership.setApproved(true);
					aspnetMembershipEntityRepository.save(membership);
				}
			}

			if (saveType ==2 && wholeSaler.getActive() && !r.getActive()) {
				//up_wa_SetVendorCloseTodaysDealAllRevoke(r.getWholeSalerID(), sessionUsrId);
				List<TodayDealEntity> todayDealList = todayDealEntityRepository.findAllByWholeSalerID(r.getWholeSalerID());
				List<TodayDealEntity> todayDealListUpdate = new ArrayList<>();
				for(TodayDealEntity todayDeal : todayDealList) {
					todayDeal.setActive(false);
					todayDeal.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
					todayDeal.setModifiedBy(sessionUsrId);

					todayDealListUpdate.add(todayDeal);
				}

				todayDealEntityRepository.saveAll(todayDealListUpdate);
			}

			wholeSaler.setActive(r.getActive());
			wholeSaler.setShopActive(r.getShopActive());
			wholeSaler.setOrderActive(r.getOrderActive());
			wholeSaler.setImageServerID(r.getImageServerID());
			wholeSaler.setDirName(r.getDirName());
			wholeSaler.setAdminWebServerID(r.getAdminWebServerID());
			wholeSaler.setCodeName(r.getCodeName());
			wholeSaler.setLastModifiedDateTime(new Timestamp(System.currentTimeMillis()));
			wholeSaler.setLastUser(sessionUsrId);
			wholeSaler.setMemo(r.getMemo());
			wholeSaler.setInHouseMemo(r.getInHouseMemo());
			wholeSaler.setOrderNotice(r.getOrderNotice());
			wholeSaler.setNoticeToAll(r.getNoticeToAll());
			wholeSaler.setSourceType(r.getSourceType());

			if (saveType == 2) {
				wholeSaler.setNewCustYN(r.getNewCustYN());
				wholeSaler.setIsADBlock(r.getIsADBlock());

				if (r.getOrderActive()) {
					setVendorNewVendorAdVendorItemAdd(r.getWholeSalerID(), sessionUsrId);
					if (wholeSaler.getActualOpenDate() == null) {
						wholeSaler.setActualOpenDate(Timestamp.valueOf(LocalDateTime.now()));
						setEntityActionLog(1, r.getWholeSalerID(), 3001);
						wholeSaler.setContractExpireDate(null);
					} else {
						String actualOpenDateTest = wholeSaler.getActualOpenDate() != null ? wholeSaler.getActualOpenDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")) : "0";
						String dateTimeNowTest = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
						int actualOpenDateTestInt = Integer.parseInt(actualOpenDateTest);
						int dateTimeNowTestInt = Integer.parseInt(dateTimeNowTest);

						if (actualOpenDateTestInt > dateTimeNowTestInt) {
							wholeSaler.setActualOpenDate(Timestamp.valueOf(LocalDateTime.now()));
							setEntityActionLog(1, r.getWholeSalerID(), 3001);
							wholeSaler.setContractExpireDate(null);

						}
					}
				} else if (!r.getOrderActive() && wholeSaler.getActualOpenDate() == null) {
					if (r.getActualOpenDate() != null) {
						String actualOpenDateTest = r.getActualOpenDate() != null ? r.getActualOpenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
						String dateTimeNowTest = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						if (actualOpenDateTest.equals(dateTimeNowTest)) {
							setVendorNewVendorAdVendorItemAdd(r.getWholeSalerID(), sessionUsrId);

							wholeSaler.setActualOpenDate(Timestamp.valueOf(LocalDateTime.now()));
							wholeSaler.setOrderActive(true);
							wholeSaler.setShopActive(true);
							wholeSaler.setActive(true);

							setEntityActionLog(1, r.getWholeSalerID(), 3001);
							wholeSaler.setContractExpireDate(null);
						} else {
							wholeSaler.setActualOpenDate(Timestamp.valueOf(r.getActualOpenDate()));
						}
					}
				} else if (!r.getOrderActive() && wholeSaler.getActualOpenDate() != null) {
					String actualOpenDateTest2 = r.getActualOpenDate() != null ? r.getActualOpenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
					String actualOpenDateTest3 = wholeSaler.getActualOpenDate() != null ? wholeSaler.getActualOpenDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";

					if (r.getActualOpenDate() != null && !actualOpenDateTest2.equals(actualOpenDateTest3)) {
						String actualOpenDateTest = r.getActualOpenDate() != null ? r.getActualOpenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
						String dateTimeNowTest = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						if (actualOpenDateTest.equals(dateTimeNowTest)) {
							setVendorNewVendorAdVendorItemAdd(r.getWholeSalerID(), sessionUsrId);

							wholeSaler.setActualOpenDate(Timestamp.valueOf(LocalDateTime.now()));
							wholeSaler.setOrderActive(true);
							wholeSaler.setShopActive(true);
							wholeSaler.setActive(true);

							setEntityActionLog(1, r.getWholeSalerID(), 3001);
							wholeSaler.setContractExpireDate(null);
						} else {
							wholeSaler.setActualOpenDate(Timestamp.valueOf(r.getActualOpenDate()));
						}
					}
				}

				try {
					wholeSaler.setTransactionFeeRate1(r.getTransactionFeeRate1());
					wholeSaler.setTransactionFeeRate2(r.getTransactionFeeRate2());
					wholeSaler.setTransactionFeeRate1Intl(r.getTransactionFeeRate1Intl());
					wholeSaler.setTransactionFeeRate2Intl(r.getTransactionFeeRate2Intl());
					wholeSaler.setTransactionFeeFixed(r.getTransactionFeeFixed());
					wholeSaler.setCommissionRate(r.getCommissionRate());

					MapWholeSalerPaymentMethodEntity mapWholeSalerPaymentMethod = null;
					if (r.getUseCreditCardPaymentService()) {
						mapWholeSalerPaymentMethod = mapWholeSalerPaymentMethodEntityRepository.findOneByWholeSalerIDAndPaymentMethodID(r.getWholeSalerID(), 100);
						if (mapWholeSalerPaymentMethod != null) {
							mapWholeSalerPaymentMethod.setPaymentMethodID(6);
							mapWholeSalerPaymentMethodEntityRepository.save(mapWholeSalerPaymentMethod);
						}
					} else {
						mapWholeSalerPaymentMethod = mapWholeSalerPaymentMethodEntityRepository.findOneByWholeSalerIDAndPaymentMethodID(r.getWholeSalerID(), 6);
						if (mapWholeSalerPaymentMethod != null) {
							mapWholeSalerPaymentMethod.setPaymentMethodID(100);
							mapWholeSalerPaymentMethodEntityRepository.save(mapWholeSalerPaymentMethod);
						}
					}

					wholeSaler.setUseCreditCardPaymentService(r.getUseCreditCardPaymentService());
					if (payoutCount > 0) {
						if (payoutSchedule == 1 || payoutSchedule == 2) {
							VendorPayoutInfoEntity vp = vendorPayoutInfoEntityRepository.findOneByWholeSalerID(r.getWholeSalerID());
							vp.setPayoutSchedule(payoutSchedule);
							vp.setMaxPayoutPerDay(maxPayoutPerDay);
							vp.setModifiedBy(sessionUsrId);
							vp.setWeekday(null);
							vp.setDayOfMonth(null);
							vendorPayoutInfoEntityRepository.save(vp);

						} else if (payoutSchedule == 3) {
							VendorPayoutInfoEntity vp = vendorPayoutInfoEntityRepository.findOneByWholeSalerID(r.getWholeSalerID());
							vp.setPayoutSchedule(payoutSchedule);
							vp.setMaxPayoutPerDay(maxPayoutPerDay);
							vp.setModifiedBy(sessionUsrId);
							vp.setWeekday(payoutScheduleWM);
							vp.setDayOfMonth(null);
							vendorPayoutInfoEntityRepository.save(vp);
						} else if (payoutSchedule == 4) {
							VendorPayoutInfoEntity vp = vendorPayoutInfoEntityRepository.findOneByWholeSalerID(r.getWholeSalerID());
							vp.setPayoutSchedule(payoutSchedule);
							vp.setMaxPayoutPerDay(maxPayoutPerDay);
							vp.setModifiedBy(sessionUsrId);
							vp.setWeekday(null);
							vp.setDayOfMonth(payoutScheduleWM);
							vendorPayoutInfoEntityRepository.save(vp);
						}
					}
				} catch (Exception ex) {
				}
			}

			wholeSaler.setIndustryType(r.getIndustryType());
			wholeSaler.setConsolidationYN(r.getConsolidationYN());
			wholeSaler.setChargedByCreditCard(r.getChargedByCreditCard());
			vendorWholeSalerEntityRepository.save(wholeSaler);

			if (!r.getActive()) {
				List<VendorAdminAccountEntity> vendorAdminAccountList = vendorAdminAccountEntityRepository.findAllByWholeSalerID(r.getWholeSalerID());
				for(VendorAdminAccountEntity va : vendorAdminAccountList) {
					AspnetMembershipEntity subAccount = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(va.getUserGUID());
					subAccount.setApproved(false);
					subAccount.setLockedOut(true);
					aspnetMembershipEntityRepository.save(subAccount);

					VendorAdminAccountEntity subAccount1 = vendorAdminAccountEntityRepository.findOneByVendorAdminAccountID(va.getVendorAdminAccountID());
					subAccount1.setActive(false);
					vendorAdminAccountEntityRepository.save(subAccount1);
				}
			}

			result = 1;


		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);
			return -99;
		}

		try {
			if (result == 1 && saveType == 1) {
				String spname = "up_Setting_Account";
				List<Object> params = new ArrayList<>();
				params.add(r.getWholeSalerID());
				params.add(sessionUsrId);
				jdbcHelperFgBilling.executeSP(spname, params);
			}
		} catch (Exception ex) {
		}

		return result;
	}

	public ResultCode setDirCompanyNameChangeHistory(Integer wholeSalerID, String sourceDirName, String targetDirName, String oldCompanyName, String newCompanyName) {
		ResultCode result = new ResultCode(false, -1, null);

		VendorDirNameChangeLogEntity trm = new VendorDirNameChangeLogEntity();

		try {
			trm.setSourceDirName(sourceDirName);
			trm.setTargetDirName(targetDirName);
			trm.setOldCompanyName(oldCompanyName);
			trm.setNewCompanyName(newCompanyName);
			trm.setCreatedOn(LocalDateTime.now());

			vendorDirNameChangeLogEntityRepository.save(trm);

			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("success");
		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);

			result.setSuccess(false);
			result.setResultCode(-1);
			result.setResultMsg("savefailure");
		}
		return result;
	}

	private int setEntityActionLogDetail(Integer entityTypeID, Integer wholeSalerID, Integer actionID, String detailLog) {
		try {
			EntityActionLogEntity actionLog = new EntityActionLogEntity();

			actionLog.setEntityTypeID(entityTypeID);
			actionLog.setActionID(actionID);
			actionLog.setEntityID(wholeSalerID);
			actionLog.setRemark(detailLog);
			actionLog.setActedOn(LocalDateTime.now());
			actionLog.setActedBy(Utility.getUsername());
			entityActionLogEntityRepository.save(actionLog);

			return 1;
		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);
			return -99;
		}
	}

	public int setEntityActionLog(Integer entityTypeID, Integer wholeSalerID, Integer actionID) {
		try {
			EntityActionLogEntity actionLog = new EntityActionLogEntity();

			actionLog.setEntityTypeID(entityTypeID);
			actionLog.setActionID(actionID);
			actionLog.setEntityID(wholeSalerID);
			actionLog.setActedOn(LocalDateTime.now());
			actionLog.setActedBy(Utility.getUsername());
			entityActionLogEntityRepository.save(actionLog);

			return 1;
		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);
			return -99;
		}
	}

	private void setVendorNewVendorAdVendorItemAdd(Integer wholeSalerId, String sessionUsrID) {
		long count = adVendorItemEntityRepository.getCountByWholeSalerID(wholeSalerId);

		if (count == 0) {
			AdVendorItemEntity item = new AdVendorItemEntity();

			item.setCategoryID(-10);
			item.setWholeSalerID(wholeSalerId);
			item.setFromDate(LocalDateTime.now());
			item.setToDate(LocalDateTime.parse("9999-12-31 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			item.setCreatedOn(LocalDateTime.now());
			item.setCreatedBy(sessionUsrID);
			item.setHowToInput(1);
			item.setHowToSell(1);
			item.setItemCount(10);
			item.setActualPrice(BigDecimal.valueOf(0.00));

			adVendorItemEntityRepository.save(item);
		}
	}

	public Integer setVendorImage(Integer wid, Integer type, String fileName, String userID) {
		Integer result = 0;

		try {
			VendorImageRequestEntity vendorImage = null;

			if (type == 5) {
				vendorImage = vendorImageRequestEntityRepository.findOneByWholeSalerIDAndVendorImageTypeID(wid, type);
			} else {
				vendorImage = vendorImageRequestEntityRepository.findOneByWholeSalerIDAndVendorImageTypeIDAndActiveTrue(wid, type);
			}

			if (vendorImage == null) {
				vendorImage = new VendorImageRequestEntity();
			}

			vendorImage.setActive(true);
			vendorImage.setOriginalFileName(fileName);
			vendorImage.setVendorImageTypeId(type);
			vendorImage.setDecidedOn(LocalDateTime.now());
			vendorImage.setRequestedOn(LocalDateTime.now());
			vendorImage.setWholesalerId(wid);
			vendorImage.setIsApproved(true);
			vendorImage.setDecidedBy(userID);

			vendorImageRequestEntityRepository.save(vendorImage);

			result = 1;

		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);
			result = -99;
		}

		return result;
	}

	public Integer delVendorImage(Integer wid, Integer type) {
		Integer result = 0;

		try {
			VendorImageRequestEntity vendorImage = vendorImageRequestEntityRepository.findOneByWholeSalerIDAndVendorImageTypeID(wid, type);
			vendorImageRequestEntityRepository.delete(vendorImage);

			result = 1;
		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);
			result = -99;
		}

		return result;
	}

	@Transactional
	public ResultCode setVendorSNSList(Integer mapID, Integer wholeSalerID, Integer socialMediaID, String url) {
		ResultCode result = new ResultCode();

		try {
			if(mapID == 0) {
				CustomerSocialMediaEntity customerSocialMedia = new CustomerSocialMediaEntity();
				customerSocialMedia.setReferenceID(wholeSalerID);
				customerSocialMedia.setUserTypeID(2);
				customerSocialMedia.setSocialMediaID(socialMediaID);
				customerSocialMedia.setSocialMediaUsername(url);
				customerSocialMediaEntityRepository.save(customerSocialMedia);

				result.setSuccess(true);
				result.setResultCode(1);
				result.setResultMsg("success");
			} else {
				CustomerSocialMediaEntity customerSocialMedia = customerSocialMediaEntityRepository.findOneByMapID(mapID);
				customerSocialMedia.setSocialMediaUsername(url);
				customerSocialMediaEntityRepository.save(customerSocialMedia);

				result.setSuccess(true);
				result.setResultCode(1);
				result.setResultMsg("success");
			}
		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);

			result.setSuccess(false);
			result.setResultCode(-1);
			result.setResultMsg("failure");
		}

		return result;
	}

	@Transactional
	public Integer setAccountLockOut(boolean active, int wholeSalerID) {
		Integer result = 0;

		try {
			WholeSalerEntity wholeSalers = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
			String wholeSalerGUID = wholeSalers.getWholeSalerGUID();

			AspnetMembershipEntity membership = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(wholeSalerGUID);
			membership.setLockedOut(active);
			membership.setApproved(true);
			membership.setFailedPasswordAttemptCount(0);
			aspnetMembershipEntityRepository.save(membership);

			result = 1;
		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);
			result = -99;
		}

		return result;
	}

	public Integer setAccountLockOutSubAccount(boolean active, int wholeSalerID) {
		Integer result = 0;

		try {
			List<VendorAdminAccountEntity> vendorAdminAccounts = vendorAdminAccountEntityRepository.findAllByWholeSalerID(wholeSalerID);

			List<AspnetMembershipEntity> membershipList = new ArrayList<>();
			for(VendorAdminAccountEntity vendorAdminAccount : vendorAdminAccounts) {
				AspnetMembershipEntity membership = aspnetMembershipEntityRepository.findOneByWholeSalerGUIDAndIsLockedOutTrue(vendorAdminAccount.getUserGUID());
				if (membership == null) continue;
				membership.setLockedOut(false);
				membershipList.add(membership);
			}
			aspnetMembershipEntityRepository.saveAll(membershipList);

			result = 1;
		} catch (Exception ex) {
			result = -99;
		}

		return result;
	}

	@Transactional
	public ResultCode setVendorCommunication(Integer communicationID, String notes, Integer wid) {
		ResultCode result = new ResultCode(false, null, null);

		try {
			if (communicationID == 0) {
				LogCommunicationEntity trm = new LogCommunicationEntity();

				trm.setIsForVendor(true);
				trm.setNotes(notes);
				trm.setRetailerID(wid);
				trm.setCreatedBy(Utility.getUsername());
				trm.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));

				logCommunicationEntityRepository.save(trm);

				result.setSuccess(true);
				result.setResultCode(1);
				result.setResultMsg("success");
			} else if (communicationID > 0) {
				LogCommunicationEntity logCommunication = logCommunicationEntityRepository.findOneByCommunicationID(communicationID);

				logCommunication.setNotes(notes);
				logCommunication.setModifiedBy(Utility.getUsername());
				logCommunication.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));

				logCommunicationEntityRepository.save(logCommunication);

				result.setSuccess(true);
				result.setResultCode(1);
				result.setResultMsg("success");
			}
		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);

			result.setSuccess(false);
			result.setResultCode(-1);
			result.setResultMsg("savefailure");
		}

		return result;
	}

	public List<VendorCommunicationList> getVendorCommunicationList(Integer wid) {
		return logCommunicationEntityRepository.findAllByRetailerIDAndIsForVendor(wid);
	}

	@Transactional
	public ResultCode delVendorCommunication(Integer communicationID) {
		ResultCode result = new ResultCode(false, null, null);
		LogCommunicationEntity trm = logCommunicationEntityRepository.findOneByCommunicationID(communicationID);

		try {
			logCommunicationEntityRepository.delete(trm);

			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("deletesuccess");
		} catch (Exception ex) {
			log.warn(ex.getMessage(),ex);

			result.setSuccess(false);
			result.setResultCode(-1);
			result.setResultMsg("deletefailure");
		}

		return result;
	}

	public GetVendorSettingResponse getVendorSetting(Integer wid) {

		GetVendorSettingResponse result = GetVendorSettingResponse.builder()
				.vendorCap(vendorCapEntityRepository.findByWholeSalerID(wid))
				.vendorCapDefault(codeVendorCapTypeEntityRepository.findVendorCapDefault())
				.vendorBlock(vendorBlockedEntityRepository.findByWholeSalerID(wid))
				.vendorBlockReason(listVendorBlockReasonEntityRepository.findVendorBlockReason())
				.vendor(vendorWholeSalerEntityRepository.findAllActive())
				.vendorSister(mapWholeSalerSisterEntityRepository.findVendorSister(wid))
				//.holdVendor(logVendorHoldEntityRepository.findByWholeSalerIDAndActiveAndHoldTo(wid))
				.holdVendor(logVendorHoldEntityRepository.findByWholeSalerIDAndActive(wid))
				.vendorHistory(entityActionLogEntityRepository.findByEntityIDAndEntityTypeID(wid))
			.build();

		return result;
	}

	@Transactional
	public ResultCode setVendorSetting(Integer wid, Integer capID, Integer vendorCapTypeID, Integer cap) {
		ResultCode result = new ResultCode(false, null, null);

		VendorCapEntity trm = new VendorCapEntity();

		try {
			if (capID == 0) {
				trm.setWholeSalerID(wid);
				trm.setVendorCapTypeID(vendorCapTypeID);
				trm.setCap(cap);
				trm.setCreatedBy(Utility.getUsername());
				trm.setCreatedOn(LocalDateTime.now());
				trm.setModifiedBy(Utility.getUsername());
				trm.setModifiedOn(LocalDateTime.now());

				vendorCapEntityRepository.save(trm);

				result.setSuccess(true);
				result.setResultCode(1);
				result.setResultMsg("success");
			} else if (capID > 0) {
				trm = vendorCapEntityRepository.findOneByVendorCapID(capID);
				trm.setCap(cap);
				trm.setModifiedBy(Utility.getUsername());
				trm.setModifiedOn(LocalDateTime.now());

				vendorCapEntityRepository.save(trm);

				result.setSuccess(true);
				result.setResultCode(1);
				result.setResultMsg("success");
			}
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);

			result.setResultCode(-1);
			result.setResultMsg("savefailure");
		}

		return result;
	}

	@Transactional
	public ResultCode setVendorBlock(Integer wholeSalerID, Integer blockReasonID, String reason) {
		ResultCode result = new ResultCode(false, null, null);

		VendorBlockedEntity trm = new VendorBlockedEntity();
		try {
			trm.setWholeSalerId(wholeSalerID);
			trm.setBlockReasonId(blockReasonID);
			trm.setBlockedOn(LocalDateTime.now());
			trm.setBlockedBy(Utility.getUsername());

			vendorBlockedEntityRepository.save(trm);

			EntityActionLogEntity trm2 = new EntityActionLogEntity();
			trm2.setEntityTypeID(9);
			trm2.setEntityID(wholeSalerID);
			trm2.setActionID(9001);
			trm2.setActedOn(LocalDateTime.now());
			trm2.setActedBy(Utility.getUsername());
			trm2.setRemark(reason);

			entityActionLogEntityRepository.save(trm2);

			List<VendorAdminAccountEntity> vendorAdminAccountList = vendorAdminAccountEntityRepository.findAllByWholeSalerID(wholeSalerID);
			List<AspnetMembershipEntity> aspnetMembershipEntityList = new ArrayList<>();
			for(VendorAdminAccountEntity va : vendorAdminAccountList) {
				AspnetMembershipEntity subAccount = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(va.getUserGUID());
				subAccount.setApproved(false);
				subAccount.setLockedOut(true);
				aspnetMembershipEntityList.add(subAccount);
			}
			aspnetMembershipEntityRepository.saveAll(aspnetMembershipEntityList);

			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("success");
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);

			result.setResultCode(-1);
			result.setResultMsg("savefailure");
		}

		return result;
	}

	public Integer setBlockVendorUpdate(Integer wholeSalerID, Integer blockReasonID) {
		Integer result = 0;

		try {
			VendorBlockedEntity retailer = vendorBlockedEntityRepository.findOneByWholeSalerID(wholeSalerID);
			retailer.setBlockReasonId(blockReasonID);
			vendorBlockedEntityRepository.save(retailer);

			result = 1;
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);

			result = -99;
		}

		return result;
	}

	@Transactional
	public ResultCode delVendorBlock(Integer blockID, Integer wholeSalerID) {
		ResultCode result = new ResultCode(false, null, null);

		try {
			vendorBlockedEntityRepository.deleteById(blockID);

			EntityActionLogEntity trm2 = new EntityActionLogEntity();
			trm2.setEntityTypeID(9);
			trm2.setEntityID(wholeSalerID);
			trm2.setActionID(9002);
			trm2.setActedOn(LocalDateTime.now());
			trm2.setActedBy(Utility.getUsername());

			entityActionLogEntityRepository.save(trm2);

			List<VendorAdminAccountEntity> vendorAdminAccountList = vendorAdminAccountEntityRepository.findAllByWholeSalerID(wholeSalerID);
			List<AspnetMembershipEntity> aspnetMembershipEntityList = new ArrayList<>();
			for(VendorAdminAccountEntity va : vendorAdminAccountList) {
				AspnetMembershipEntity subAccount = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(va.getUserGUID());
				subAccount.setApproved(true);
				subAccount.setLockedOut(false);
				aspnetMembershipEntityList.add(subAccount);
			}
			aspnetMembershipEntityRepository.saveAll(aspnetMembershipEntityList);

			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("deletesucecss");
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);

			result.setResultCode(-1);
			result.setResultMsg("deletefailure");
		}

		return result;
	}

	@Transactional
	public ResultCode setHoldVendor(Integer wholeSalerID, Integer holdType, Boolean active, Timestamp holdFrom, Timestamp holdTo) {
		ResultCode result = new ResultCode(false, null, null);

		try {
			if (holdType == 1) {
				LogVendorHoldEntity trm = new LogVendorHoldEntity();
				trm.setWholeSalerID(wholeSalerID);
				trm.setHoldFrom(holdFrom);
				trm.setHoldTo(holdTo);
				trm.setActive(active);
				trm.setCreatedBy(Utility.getUsername());
				trm.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));

				logVendorHoldEntityRepository.save(trm);
			} else if (holdType == 2) {
				WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
				
				if(active) {
					if(holdFrom.toLocalDateTime().plusDays(1).minusSeconds(1).isAfter(LocalDateTime.now())) {
						trm.setContractExpireDate(Timestamp.valueOf(holdFrom.toLocalDateTime().plusDays(1).minusSeconds(1)));
						trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
						//trm.setActualOpenDate(null);
					} else {
						trm.setContractExpireDate(Timestamp.valueOf(LocalDateTime.now()));
						trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
						trm.setOrderActive(false);
						trm.setShopActive(false);
						trm.setActive(false);
						//trm.setActualOpenDate(null);
					}
				} else {
					trm.setContractExpireDate(null);
					trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
					trm.setShopActive(true);
					trm.setActive(true);
				}

				vendorWholeSalerEntityRepository.save(trm);
			} else if (holdType == 3) {
				WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
				trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
				trm.setOrderActive(false);
				trm.setShopActive(true);
				trm.setActive(true);

				vendorWholeSalerEntityRepository.save(trm);
			} else if (holdType == 4) {
				WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
				trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
				trm.setOrderActive(true);

				vendorWholeSalerEntityRepository.save(trm);
			}

			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("success");
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);

			result.setResultCode(-1);
			result.setResultMsg("savefailure");
		}

		return result;
	}

	public Integer setHoldVendorUpdate(Integer wholeSalerID,Integer logID, Boolean active, Timestamp holdFrom, Timestamp holdTo) {
		Integer result = 0;

		try {
			LogVendorHoldEntity lvh = logVendorHoldEntityRepository.findById(logID).get();
		
			if(active) {
				lvh.setHoldFrom(holdFrom);
				lvh.setHoldTo(holdTo);				
			}
		
			lvh.setActive(active);	
			logVendorHoldEntityRepository.save(lvh);
			
			if(!active) {
				WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
				trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
				trm.setLastUser(Utility.getUsername());
				trm.setOrderActive(true);

				vendorWholeSalerEntityRepository.save(trm);			
			}
			else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				
				int result1 = holdFrom.compareTo(timestamp);
				
				if (result1 == -1 || result1 == 0) {
					WholeSalerEntity trm1 = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
					trm1.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
					trm1.setOrderActive(false);
					trm1.setShopActive(true);
					trm1.setActive(true);		
					trm1.setLastUser(Utility.getUsername());
				
					vendorWholeSalerEntityRepository.save(trm1);
				}
				else {
					WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
					trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
					trm.setLastUser(Utility.getUsername());
					trm.setOrderActive(true);
	
					vendorWholeSalerEntityRepository.save(trm);					
				}
			}
			result = 1;
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);
			result = -99;
		}

		return result;
	}

	public Boolean vendorDirNameCheck(Integer wholeSalerID, String dirName) {
		long resultCount = wholeSalerEntityRepository.countByDirNameAndNotWholeSalerID(wholeSalerID, dirName);

		return resultCount > 0;
	}

	public Long vendorCodeNameCheck(Integer wholeSalerID, String codeName) {
		long resultCount = vendorWholeSalerEntityRepository.countByCodeNameAndNotWholeSalerID(wholeSalerID, codeName);

		return resultCount;
	}

	public List<VendorSister> getVendorSister(Integer wid) {
		List<VendorSister> result = mapWholeSalerSisterEntityRepository.findVendorSister(wid);

		return result;
	}

	public List<Integer> getVendorSisterChk(Integer wid, Integer sisterId) {
		List<Integer> result = mapWholeSalerSisterEntityRepository.findMapIDByWholeSalerIDAndSisterWholeSalerID(wid, sisterId);

		return result;
	}

	@Transactional
	public ResultCode setVendorSister(Integer wid, Integer sisterId) {
		ResultCode result = new ResultCode(false, null, null);

		MapWholeSalerSisterEntity trm = new MapWholeSalerSisterEntity();

		try {
			trm.setWholeSalerID(wid);
			trm.setSisterWholeSalerID(sisterId);
			trm.setCreatedBy(Utility.getUsername());
			trm.setCreatedOn(LocalDateTime.now());

			mapWholeSalerSisterEntityRepository.save(trm);

			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("success");
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);
			result.setResultCode(-1);
			result.setResultMsg("savefailure");
		}

		return result;
	}

	@Transactional
	public ResultCode delVendorSister(Integer mapID) {
		ResultCode result = new ResultCode(false, null, null);

		try {
			MapWholeSalerSisterEntity trm = mapWholeSalerSisterEntityRepository.findById(mapID).get();
			mapWholeSalerSisterEntityRepository.delete(trm);

			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("deletesuccess");
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);
			result.setResultCode(-1);
			result.setResultMsg("deletefailure");
		}

		return result;
	}

	public List<SecurityUserEntity> getVendorSecurityUsers() {
		return securityUserEntityRepository.findAllActive();
	}

	public List<VendorContractHistory> getVendorContractHistoryList(Integer wholeSalerID) {
		return vendorContractEntityRepository.findContractHistoryListByWholeSalerID(wholeSalerID);
	}

	@Transactional
	public ResultCode setVendorContract(Integer vendorContractID, Integer wholeSalerID, Integer contractTypeID, BigDecimal setupFee, BigDecimal lastMonthServiceFee,
									BigDecimal monthlyFee, Integer photoPlanID, Boolean useModel, String useModelStyle, Integer monthlyItems, BigDecimal commissionRate,
									Integer repID, Boolean perorder, Timestamp vendorContractFrom, Boolean vendorContractRowAdd, String memo, Boolean isSetupFeeWaived,
									Boolean isLastMonthServiceFeeWaived, Integer vendorContractPlanID, Integer commissionBaseDateCode) {
		ResultCode result = new ResultCode(false, null, null);

		VendorContractEntity trm = null;
		WholeSalerEntity wholeSaler = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);

		Integer vendorTypeID = contractTypeID != 5 ? 1 : 2;

		try {
			if (vendorContractID == 0) {
				trm = vendorContractEntityRepository.findOneByWholeSalerID(wholeSalerID);
				if (trm == null) {
					trm = new VendorContractEntity();
				}
				trm.setWholeSalerID(wholeSalerID);
				trm.setContractTypeID(contractTypeID);
				trm.setSetupFee(setupFee);
				trm.setLastMonthServiceFee(lastMonthServiceFee);
				trm.setMonthlyFee(monthlyFee);
				trm.setPhotoPlanID(photoPlanID);
				trm.setUseModel(useModel);
				trm.setUseModelStyle(useModelStyle);
				trm.setMonthlyItems(monthlyItems);
				trm.setCommissionRate(commissionRate);
				trm.setRepID(repID);
				trm.setPerorder(perorder);
				trm.setMemo(memo);
				trm.setCreatedBy(Utility.getUsername());
				trm.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
				trm.setFromContractDate(vendorContractFrom);
				trm.setIsSetupFeeWaived(isSetupFeeWaived);
				trm.setIsLastMonthServiceFeeWaived(isLastMonthServiceFeeWaived);
				trm.setVendorContractPlanId(vendorContractPlanID);
				trm.setCommissionBaseDateCode(commissionBaseDateCode);

				vendorContractEntityRepository.save(trm);

				wholeSaler.setVendorType(vendorTypeID);
				vendorWholeSalerEntityRepository.save(wholeSaler);

				result.setSuccess(true);
				result.setResultCode(1);
				result.setResultMsg("success");
			} else {
				if (vendorContractRowAdd) {
					trm = vendorContractEntityRepository.findOneByVendorContractID(vendorContractID);
					trm.setModifiedBy(Utility.getUsername());
					trm.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
					if (vendorContractFrom != null) trm.setToContractDate(Timestamp.valueOf(vendorContractFrom.toLocalDateTime().minusDays(1)));
					else trm.setToContractDate(vendorContractFrom);
					vendorContractEntityRepository.save(trm);

					VendorContractEntity trm2 = new VendorContractEntity();
					trm2.setWholeSalerID(wholeSalerID);
					trm2.setContractTypeID(contractTypeID);
					trm2.setSetupFee(setupFee);
					trm2.setLastMonthServiceFee(lastMonthServiceFee);
					trm2.setMonthlyFee(monthlyFee);
					trm2.setPhotoPlanID(photoPlanID);
					trm2.setUseModel(useModel);
					trm2.setUseModelStyle(useModelStyle);
					trm2.setMonthlyItems(monthlyItems);
					trm2.setCommissionRate(commissionRate);
					trm2.setRepID(repID);
					trm2.setPerorder(perorder);
					trm2.setMemo(memo);
					trm2.setCreatedBy(Utility.getUsername());
					trm2.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
					trm2.setModifiedBy(Utility.getUsername());
					trm2.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
					trm2.setFromContractDate(vendorContractFrom);
					trm2.setIsSetupFeeWaived(isSetupFeeWaived);
					trm2.setIsLastMonthServiceFeeWaived(isLastMonthServiceFeeWaived);
					trm2.setVendorContractPlanId(vendorContractPlanID);
					trm2.setCommissionBaseDateCode(commissionBaseDateCode);
					vendorContractEntityRepository.save(trm2);

					wholeSaler.setVendorType(vendorTypeID);
					vendorWholeSalerEntityRepository.save(wholeSaler);

					result.setSuccess(true);
					result.setResultCode(1);
					result.setResultMsg("success");
				} else {
					trm = vendorContractEntityRepository.findOneByVendorContractID(vendorContractID);
					trm.setWholeSalerID(wholeSalerID);
					trm.setContractTypeID(contractTypeID);
					trm.setSetupFee(setupFee);
					trm.setLastMonthServiceFee(lastMonthServiceFee);
					trm.setMonthlyFee(monthlyFee);
					trm.setPhotoPlanID(photoPlanID);
					trm.setUseModel(useModel);
					trm.setUseModelStyle(useModelStyle);
					trm.setMonthlyItems(monthlyItems);
					trm.setCommissionRate(commissionRate);
					trm.setRepID(repID);
					trm.setPerorder(perorder);
					trm.setMemo(memo);
					trm.setModifiedBy(Utility.getUsername());
					trm.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
					trm.setFromContractDate(vendorContractFrom);
					trm.setIsSetupFeeWaived(isSetupFeeWaived);
					trm.setIsLastMonthServiceFeeWaived(isLastMonthServiceFeeWaived);
					trm.setVendorContractPlanId(vendorContractPlanID);
					trm.setCommissionBaseDateCode(commissionBaseDateCode);

					vendorContractEntityRepository.save(trm);

					wholeSaler.setVendorType(vendorTypeID);
					vendorWholeSalerEntityRepository.save(wholeSaler);

					result.setSuccess(true);
					result.setResultCode(1);
					result.setResultMsg("success");
				}
			}
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);
			result.setResultCode(-1);
			result.setResultMsg("failure");
		}

		return result;
	}

	public ResultCode setVendorSettingAccount(Integer wid) {
		ResultCode result = new ResultCode(false, null, null);

		try {
			String spname = "up_Setting_Account";
			List<Object> params = new ArrayList<>();
			params.add(wid);
			params.add(Utility.getUsername());
			jdbcHelperFgBilling.executeSP(spname, params);

			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("success");
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);
			result.setResultCode(-1);
			result.setResultMsg("savefailure");
		}

		return result;
	}

	public JsonResponse getVendorAdminAccountList(Integer wid) {
		JsonResponse<List<VendorAdminAccount>> response = new JsonResponse<>(false, null, null);

		try {
			List<VendorAdminAccount> vendorAdminAccounts = wholeSalerEntityRepository.findVendorAdminAccountList(wid);

			response.setSuccess(true);
			response.setData(vendorAdminAccounts);
		} catch (Exception e) {
			response.setMessage("fail");
			log.warn(e.getMessage(), e);
		}

		return response;
	}

	public GetVendorAdminAccountLogListResponse getVendorAdminAccountLogList(
			Integer pageNum,
			Integer pageSize,
			Integer wholeSalerID,
			String userID,
			LocalDate date,
			String ipAddress,
			String orderBy
	) {
		Page<VendorAdminLoginLogEntity> result = vendorAdminLoginLogEntityRepository.findVendorAdminLoginLog(pageNum, pageSize, wholeSalerID, userID, date, ipAddress, orderBy);

		return GetVendorAdminAccountLogListResponse.builder()
				.total(Total.builder().recCnt((int) result.getTotalElements()).build())
				.vendorAdminLoginLogs(result.getContent())
				.build();
	}

	public GetVendorGroupingResponse getVendorGrouping(
			Integer wholesalerid,
			String companyType,
			String vendorType,
			String keyword,
			String categorys,
			String alphabet) {
		wholesalerid = wholesalerid == null ? 0 : wholesalerid;
		companyType = StringUtils.isEmpty(companyType) ? "" : companyType;
		keyword = StringUtils.isEmpty(keyword) ? " " : keyword;
		categorys = StringUtils.isEmpty(categorys) ? "" : categorys;
		String[] categoryList = categorys.replace("'", "").split(",");
		ArrayList<Integer> categoryIntegerList = new ArrayList<>();
		for (String t : categoryList) {
			if (StringUtils.isNotEmpty(t)) {
				categoryIntegerList.add(Integer.valueOf(t));
			}
		}
		alphabet = StringUtils.isEmpty(alphabet) ? "" : alphabet;
		vendorType = StringUtils.isEmpty(vendorType) ? "" : vendorType;

		if (vendorType.equalsIgnoreCase("email")) {
			vendorType = vendorType.toLowerCase();
		} else {
			vendorType = vendorType.substring(0, 1).toLowerCase() + vendorType.substring(1);
		}


		List<VendorGroupingSelete> vendorGroupingSeletes = vendorWholeSalerEntityRepository.findListVendorGroupingSelect(wholesalerid, null, keyword, categoryIntegerList, alphabet, vendorType);
		List<VendorGroupingUnSelete> vendorGroupingUnSeletes = vendorWholeSalerEntityRepository.findListVendorGroupingUnSelect(wholesalerid, null, keyword, categoryIntegerList, alphabet, vendorType);

		return GetVendorGroupingResponse.builder()
				.vendorGroupingSelete(vendorGroupingSeletes)
				.vendorGroupingUnSelete(vendorGroupingUnSeletes)
				.build();
	}

	public Integer setVendorGrouping(Integer wholeSalerID, String saveIds, String deleteIds) {
		Integer result = null;

		if (StringUtils.isEmpty(saveIds) && StringUtils.isEmpty(deleteIds))
			return null;

		if (StringUtils.isNotEmpty(deleteIds)) {
			String[] deleteIDList = deleteIds.split(",");
			ArrayList<Integer> widList = new ArrayList<>();
			for(String id : deleteIDList) {
				if (StringUtils.isNotEmpty(id))
					widList.add(Integer.valueOf(id));
			}
			List<MapWholeSalerGroupEntity> deleteEntities = mapWholeSalerGroupEntityRepository.findAllByIds(widList);

			mapWholeSalerGroupEntityRepository.deleteAll(deleteEntities);

			result = 1;
		}

		if (StringUtils.isNotEmpty(saveIds)) {
			String[] saveIDList = saveIds.split(",");

			List<MapWholeSalerGroupEntity> insertEntities = new ArrayList<>();
			for (String id : saveIDList) {
				if(StringUtils.isNotEmpty(id)) {
					MapWholeSalerGroupEntity WG = new MapWholeSalerGroupEntity();
					WG.setWholeSalerID(wholeSalerID);
					WG.setWholeSalerID2(Integer.valueOf(id));

					insertEntities.add(WG);
				}
			}

			mapWholeSalerGroupEntityRepository.saveAll(insertEntities);

			result = 1;
		}

		return result;
	}

	public GetAssignedUserListResponse getAssignedUserList() {
		List<AssignedUser> data = securityUserEntityRepository.findAssignedUserList();

		return GetAssignedUserListResponse.builder().assignedUserList(data).build();
	}

	public GetVendorListResponse getVendorList(GetVendorListParameter vendorListParam) {
		Page<VendorList> result = vendorWholeSalerEntityRepository.getVendorListWithCount(vendorListParam);
		List<Total> count = Collections.singletonList(new Total((int) result.getTotalElements()));

		return GetVendorListResponse.builder().recCnt(count).vendorList(result.getContent()).build();
	}

	public GetVendorListCSVResponse getvendorlistcsv(GetVendorListParameter vendorListParam) {
		List<VendorListCSV> result = vendorWholeSalerEntityRepository.getVendorListCSVWithCount(vendorListParam);

		return GetVendorListCSVResponse.builder().vendorListCSV(result).build();
	}

	public Integer setRetailerRatingActive(SetRetailerRatingActiveParameter param) {
		Integer retailerID = param.getRetailerID();
		Boolean active = param.getActive();

		try {
			RetailerRatingEntity r = retailerRatingEntityRepository.findById(retailerID).get();
			r.setActive(active);

			retailerRatingEntityRepository.save(r);
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			return -1;
		}

		return 1;
	}	
}
