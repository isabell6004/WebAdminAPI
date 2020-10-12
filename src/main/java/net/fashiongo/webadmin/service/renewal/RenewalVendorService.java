package net.fashiongo.webadmin.service.renewal;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.dao.primary.VendorEntityRepository;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.entity.primary.vendor.VendorProductRow;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.response.*;
import net.fashiongo.webadmin.data.repository.primary.*;
import net.fashiongo.webadmin.data.repository.primary.form.FashionGoFormEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.form.FormOrderingType;
import net.fashiongo.webadmin.data.repository.primary.vendor.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import net.fashiongo.webadmin.service.ApiService;
import net.fashiongo.webadmin.service.vendor.VendorNewService;
import net.fashiongo.webadmin.service.vendor.VendorBlockNewService;
import net.fashiongo.webadmin.service.vendor.VendorInfoNewService;
import net.fashiongo.webadmin.service.vendor.VendorSeoNewService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.fashiongo.webadmin.data.model.vendor.Vendor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RenewalVendorService extends ApiService {

    private final VendorInfoNewService vendorInfoNewService;
    private final VendorBlockNewService vendorBlockNewService;
    private final VendorSeoNewService vendorSeoNewService;
	private final VendorProductRepository vendorProductRepository;
    private final VendorImageRequestEntityRepository vendorImageRequestEntityRepository;
    private final FashionGoFormEntityRepository fashionGoFormEntityRepository;
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
    private final AspnetMembershipEntityRepository aspnetMembershipEntityRepository;
    private final VendorAdminAccountEntityRepository vendorAdminAccountEntityRepository;
    private final EntityActionLogEntityRepository entityActionLogEntityRepository;
    private final CustomerSocialMediaEntityRepository customerSocialMediaEntityRepository;
    private final LogCommunicationEntityRepository logCommunicationEntityRepository;
    private final VendorCapEntityRepository vendorCapEntityRepository;
    private final CodeVendorCapTypeEntityRepository codeVendorCapTypeEntityRepository;
    private final ListVendorBlockReasonEntityRepository listVendorBlockReasonEntityRepository;
    private final MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository;
    private final LogVendorHoldEntityRepository logVendorHoldEntityRepository;
    private final VendorAdminLoginLogEntityRepository vendorAdminLoginLogEntityRepository;
    private final RetailerRatingEntityRepository retailerRatingEntityRepository;
    private final VendorNewService vendorNewService;
    private final VendorEntityRepository vendorEntityRepository;

    @Autowired
    public RenewalVendorService(VendorInfoNewService vendorInfoNewService,
                                VendorBlockNewService vendorBlockNewService,
                                VendorSeoNewService vendorSeoNewService,
    		                    VendorProductRepository vendorProductRepository,
                                VendorImageRequestEntityRepository vendorImageRequestEntityRepository,
                                FashionGoFormEntityRepository fashionGoFormEntityRepository,
                                SecurityUserEntityRepository securityUserEntityRepository,
                                CodeWholeSalerCompanyTypeEntityRepository codeWholeSalerCompanyTypeEntityRepository,
                                CodeCountryEntityRepository codeCountryEntityRepository,
                                MapWholeSalerPaymentMethodEntityRepository mapWholeSalerPaymentMethodEntityRepository,
                                WholeShipMethodEntityRepository wholeShipMethodEntityRepository,
                                VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository,
                                VendorNameHistoryLogEntityRepository vendorNameHistoryLogEntityRepository,
                                ListSocialMediaEntityRepository listSocialMediaEntityRepository,
                                VendorPayoutInfoEntityRepository vendorPayoutInfoEntityRepository,
                                ListVendorDocumentTypeEntityRepository listVendorDocumentTypeEntityRepository,
                                CodeVendorIndustryEntityRepository codeVendorIndustryEntityRepository,
                                AspnetMembershipEntityRepository aspnetMembershipEntityRepository,
                                VendorAdminAccountEntityRepository vendorAdminAccountEntityRepository,
                                EntityActionLogEntityRepository entityActionLogEntityRepository,
                                CustomerSocialMediaEntityRepository customerSocialMediaEntityRepository,
                                LogCommunicationEntityRepository logCommunicationEntityRepository,
                                VendorCapEntityRepository vendorCapEntityRepository,
                                CodeVendorCapTypeEntityRepository codeVendorCapTypeEntityRepository,
                                ListVendorBlockReasonEntityRepository listVendorBlockReasonEntityRepository,
                                MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository,
                                LogVendorHoldEntityRepository logVendorHoldEntityRepository,
                                VendorAdminLoginLogEntityRepository vendorAdminLoginLogEntityRepository,
                                RetailerRatingEntityRepository retailerRatingEntityRepository,
                                VendorNewService vendorNewService,
                                VendorEntityRepository vendorEntityRepository) {
        this.vendorInfoNewService = vendorInfoNewService;
        this.vendorBlockNewService = vendorBlockNewService;
        this.vendorSeoNewService = vendorSeoNewService;
		this.vendorProductRepository = vendorProductRepository;
        this.vendorImageRequestEntityRepository = vendorImageRequestEntityRepository;
        this.fashionGoFormEntityRepository = fashionGoFormEntityRepository;
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
        this.aspnetMembershipEntityRepository = aspnetMembershipEntityRepository;
        this.vendorAdminAccountEntityRepository = vendorAdminAccountEntityRepository;
        this.entityActionLogEntityRepository = entityActionLogEntityRepository;
        this.customerSocialMediaEntityRepository = customerSocialMediaEntityRepository;
        this.logCommunicationEntityRepository = logCommunicationEntityRepository;
        this.vendorCapEntityRepository = vendorCapEntityRepository;
        this.codeVendorCapTypeEntityRepository = codeVendorCapTypeEntityRepository;
        this.listVendorBlockReasonEntityRepository = listVendorBlockReasonEntityRepository;
        this.mapWholeSalerSisterEntityRepository = mapWholeSalerSisterEntityRepository;
        this.logVendorHoldEntityRepository = logVendorHoldEntityRepository;
        this.vendorAdminLoginLogEntityRepository = vendorAdminLoginLogEntityRepository;
        this.retailerRatingEntityRepository = retailerRatingEntityRepository;
        this.vendorNewService = vendorNewService;
        this.vendorEntityRepository = vendorEntityRepository;
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

    public VendorFormListResponse getVendorFormsList(GetVendorFormsListParameter parameters) {
        // In reality, it is sorted in the front-end app.
        // FormOrderingType.getFromStringValue(parameters.getOrderBy())
        List<FashionGoFormEntity> forms = fashionGoFormEntityRepository.getForms(FormOrderingType.FASHION_GO_FORM_ID_DESC);
        return VendorFormListResponse.builder()
                .fashionGoFormList(forms.stream().map(VendorFormResponse::convert).collect(Collectors.toList()))
                .build();
    }

    public GetVendorDetailInfoDataResponse getVendorDetailInfoData(Integer wholeSalerID) {
        VendorResponse vendorResponse = vendorNewService.getVendorById(Long.valueOf(wholeSalerID));

        VendorDetailDate vendorDetailDate = VendorDetailDate.builder()
                        .actualOpenDate(Optional.ofNullable(vendorResponse.getSetting().getOpenDate()).orElse(null))
                        .lastModifiedDateTime(Optional.ofNullable(vendorResponse.getSetting().getModifiedOn()).orElse(null))
                        .wholeSalerId(vendorResponse.getVendorId().intValue())
                        .lastUser(vendorResponse.getSetting().getModifiedBy())
                        .userID(vendorResponse.getCreatedBy())
                        .build();

        String securityUserName = null;
        //old table
        /*String securityUserName = vendorContractEntityRepository.findAllByWholeSalerId(wholeSalerID)
                .stream()
                .findFirst()
                .map(vendorContractEntity -> {
                            return vendorContractEntity.getRepID() != null ? securityUserEntityRepository.findById(vendorContractEntity.getRepID())
                                    .map(SecurityUserEntity::getUserName)
                                    .orElse(null) : null;
                        }
                ).orElse(null);*/

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
                        .countryID(entity.getCountryID())
                        .build())
                .collect(Collectors.toList());


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
        if (wholesalerId == null) {
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
        if (wholesalerId == null) {
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
        return vendorEntityRepository.findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
//        return wholeSalerEntityRepository.findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
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
                .vendorSeoInfoResponse(vendorSeoNewService.inquiryVendorSeo((long)wholesalerID))
                .build();
    }
    @Transactional
    public Integer setAccountLockOut(boolean active, int wholeSalerID) {
        Integer result = 0;

        try {
            String wholeSalerGUID = vendorEntityRepository.findByVendorId((long) wholeSalerID).getGuid();

            AspnetMembershipEntity membership = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(wholeSalerGUID);
            membership.setLockedOut(active);
            membership.setApproved(true);
            membership.setFailedPasswordAttemptCount(0);
            aspnetMembershipEntityRepository.save(membership);

            result = 1;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            result = -99;
        }

        return result;
    }

    public Integer setAccountLockOutSubAccount(boolean active, int wholeSalerID) {
        Integer result = 0;

		try {
			List<VendorAdminAccountEntity> vendorAdminAccounts = vendorAdminAccountEntityRepository.findAllByWholeSalerID(wholeSalerID);

            List<AspnetMembershipEntity> membershipList = new ArrayList<>();
            for (VendorAdminAccountEntity vendorAdminAccount : vendorAdminAccounts) {
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
            log.warn(ex.getMessage(), ex);

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
            log.warn(ex.getMessage(), ex);

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
                .vendor(ActiveVendorResponse.create(vendorEntityRepository.getActiveVendors()))
				.vendorSister(mapWholeSalerSisterEntityRepository.findVendorSister(wid))
				.holdVendor(logVendorHoldEntityRepository.findByWholeSalerIDAndActive(wid))
				.vendorHistory(entityActionLogEntityRepository.findByEntityIDAndEntityTypeID(wid))
                .vendorSettingDetailResponse(vendorInfoNewService.getVendorSettingDetail((long)wid))
                .vendorBlockInfoResponse(vendorInfoNewService.getVendorBlockInfo((long)wid))
                .codeVendorBlockReasonList(vendorBlockNewService.getCodeVendorBlockReason((long)wid))
			.build();
        return result;
    }

    public Boolean vendorDirNameCheck(Integer wholeSalerID, String dirName) {
        long resultCount = vendorEntityRepository.countByDirNameAndNotWholeSalerID(wholeSalerID, dirName);

        return resultCount > 0;
    }

    public Long vendorCodeNameCheck(Integer wholeSalerID, String codeName) {
        long resultCount = vendorWholeSalerEntityRepository.countByCodeNameAndNotWholeSalerID(wholeSalerID, codeName);

        return resultCount;
    }

    public List<SecurityUserEntity> getVendorSecurityUsers() {
        return securityUserEntityRepository.findAllActive();
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
            List<VendorAdminAccount> vendorAdminAccounts = vendorEntityRepository.findVendorAdminAccountList(wid);

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
