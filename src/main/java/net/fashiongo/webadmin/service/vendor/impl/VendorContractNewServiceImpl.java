package net.fashiongo.webadmin.service.vendor.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.ContractPlansResponse;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorContractResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetContractPlansResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorContractDocumentHistoryResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorContractHistoryListResponse;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VendorContractNewServiceImpl implements VendorContractNewService {

    private HttpClientWrapper httpCaller;

    private final ObjectMapper mapper = new ObjectMapper();

    public VendorContractNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void createVendorContractDocument(Integer vendorId, SetVendorContractDocumentParameter request, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/contracts/" + request.getVendorContractID() + "/documents";
        ContractDocumentCommand newRequest = new ContractDocumentCommand(request);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public void modifyVendorContractDocument(Integer vendorId, SetVendorContractDocumentParameter request, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/contracts/" + request.getVendorContractID() + "/documents";
        ContractDocumentCommand newRequest = new ContractDocumentCommand(request);
        httpCaller.put(endpoint, newRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public void deleteVendorContractDocument(Integer vendorId, Long contractId, List<Long> documentIds, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/contracts/" + contractId + "/documents/delete";
        ContractDocumentCommand newRequest = new ContractDocumentCommand(documentIds);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public void reviseContract(Long originalVendorContractHistoryId, Long revisedVendorContractHistoryId, SetVendorContractParameter request, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + request.getWholeSalerID() + "/contracts/" + originalVendorContractHistoryId + "/revise";
        log.debug("call the vendor api:{}", endpoint);
        ReviseContractHistoryCommand newRequest = new ReviseContractHistoryCommand(request, originalVendorContractHistoryId, revisedVendorContractHistoryId);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public void createContract(Long originalVendorContractHistoryId, SetVendorContractParameter request, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + request.getWholeSalerID() + "/contracts";
        log.debug("call the vendor api:{}", endpoint);
        // temp log
        log.warn("contract request : {}, {}, {}", request.getWholeSalerID(), originalVendorContractHistoryId, request.getVendorContractPlanID());
        ContractHistoryCommand newRequest = new ContractHistoryCommand(request, originalVendorContractHistoryId);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public void modifyContract(Long originalVendorContractHistoryId, SetVendorContractParameter request, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + request.getWholeSalerID() + "/contracts/" + originalVendorContractHistoryId;
        log.debug("call the vendor api:{}", endpoint);
        ContractHistoryCommand newRequest = new ContractHistoryCommand(request, originalVendorContractHistoryId);
        httpCaller.put(endpoint, newRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public VendorContractResponse inquiryVendorContract(Integer vendorId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/contracts/recently";

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        String responseBody = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

        try {
            mapper.registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            FashionGoApiResponse<GetVendorContractResponse> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<GetVendorContractResponse>>() {});
            if (fashionGoApiResponse.getHeader().isSuccessful()) {
                return fashionGoApiResponse.getData().getVendorContract();
            } else {
                throw new RuntimeException("fail to get recently vendor contract in new fashiongo api");
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to get recently vendor contract in new fashiongo api");
        }
    }

    @Override
    public List<ContractPlansResponse> inquiryContractPlans() {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/contractplans";

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        String responseBody = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

        try {
            mapper.registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            FashionGoApiResponse<GetContractPlansResponse> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<GetContractPlansResponse>>() {});
            if (fashionGoApiResponse.getHeader().isSuccessful()) {
                return fashionGoApiResponse.getData().getContractPlansResponseList();
            } else {
                throw new RuntimeException("fail to get contract plan list in new fashiongo api");
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to get contract plan list in new fashiongo api");
        }
    }

    @Override
    public VendorContractDocumentHistoryResponse getContractDocumentHistory(Long contractHistoryId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/contracts/" + contractHistoryId + "/document/history";
        log.debug("call the vendor api:{}", endpoint);

        WebAdminLoginUser userInfo = Utility.getUserInfo();

        String responseBody = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

        try {
            mapper.registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            FashionGoApiResponse<VendorContractDocumentHistoryResponse> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<VendorContractDocumentHistoryResponse>>() {});
            if (fashionGoApiResponse.getHeader().isSuccessful()) {
                return fashionGoApiResponse.getData();
            } else {
                throw new RuntimeException("fail to get document history in new fashiongo api");
            }
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
            throw new RuntimeException("fail to get document history in new fashiongo api");
        }
    }

    @Override
    public VendorContractHistoryListResponse getContractHistoryList(Long vendorId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/contracts/history";
        log.debug("call the vendor api:{}", endpoint);

        WebAdminLoginUser userInfo = Utility.getUserInfo();

        String responseBody = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

        try {
            mapper.registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            FashionGoApiResponse<VendorContractHistoryListResponse> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<VendorContractHistoryListResponse>>() {});
            if (fashionGoApiResponse.getHeader().isSuccessful()) {
                return fashionGoApiResponse.getData();
            } else {
                throw new RuntimeException("fail to get contract history in new fashiongo api");
            }
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
            throw new RuntimeException("fail to get contract history in new fashiongo api");
        }
    }

    @Override
    public GetVendorContractResponse getVendorContractIncludedOpenDate(Integer vendorId, LocalDateTime actualOpenDate) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/contracts";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("openDate", actualOpenDate)
                .build(false);

        log.debug("call the vendor api:{}", builder.toUriString());


        WebAdminLoginUser userInfo = Utility.getUserInfo();

        String responseBody = httpCaller.get(builder.toUriString(), FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

        try {
            mapper.registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            FashionGoApiResponse<GetVendorContractResponse> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<GetVendorContractResponse>>() {});
            if (fashionGoApiResponse.getHeader().isSuccessful()) {
                return fashionGoApiResponse.getData();
            } else {
                throw new RuntimeException("fail to get contract in new fashiongo api");
            }
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
            throw new RuntimeException("fail to get contract in new fashiongo api");
        }
    }


    @Getter
    private class ReviseContractHistoryCommand extends ContractHistoryCommand {

        private Long revisedVendorContractHistoryId;

        private ReviseContractHistoryCommand(SetVendorContractParameter request, Long originalVendorContractHistoryId, Long revisedVendorContractHistoryId) {
            super(request, originalVendorContractHistoryId);
            this.revisedVendorContractHistoryId = revisedVendorContractHistoryId;
        }
    }

    @Getter
    private class ContractHistoryCommand {

        private Long contractHistoryId;

        private Integer vendorId;
        private Long planId;

        private BigDecimal setupFee;
        private Boolean isSetupFeeWaived;

        private BigDecimal lastMonthServiceFee;
        private Boolean isLastMonthServiceFeeWaived;
        private BigDecimal monthlyFee;
        private Integer monthlyItemCap;

        private Integer accountExecutiveId;

        private BigDecimal commissionRate;
        private String dateFrom;

        private String memo;
        private Boolean isContractRevised;

        private Integer commissionBaseDateCode;

        private ContractHistoryCommand(SetVendorContractParameter request, Long originalVendorContractHistoryId) {
            this.vendorId = request.getWholeSalerID();
            this.contractHistoryId = originalVendorContractHistoryId;
            this.planId = Long.valueOf(request.getVendorContractPlanID());
            this.setupFee = request.getSetupFee();
            this.isSetupFeeWaived = Optional.ofNullable(request.getIsSetupFeeWaived()).orElse("").equalsIgnoreCase("1");
            this.lastMonthServiceFee = request.getLastMonthServiceFee();
            this.isLastMonthServiceFeeWaived = Optional.ofNullable(request.getIsLastMonthServiceFeeWaived()).orElse("").equalsIgnoreCase("1");
            this.monthlyFee = request.getMonthlyFee();
            this.monthlyItemCap = request.getMonthlyItems();
            this.accountExecutiveId = request.getRepID();
            this.commissionRate = request.getCommissionRate();
            this.dateFrom = SetVendorContractParameter.getVendorContractFrom(request.getVendorContractFrom()).toLocalDateTime().toString();
            this.memo = request.getMemo();
            this.isContractRevised = request.getVendorContractRowAdd();
            this.commissionBaseDateCode = request.getCommissionBaseDateCode();
        }
    }

    @Getter
    private class ContractDocumentCommand {

        private Integer typeCode;
        private String fileName1;
        private String fileName2;
        private String fileName3;
        private String note;
        private String receivedBy;
        private Long documentId;
        private Long contractHistoryId;

        private List<Long> documentIds;

        private ContractDocumentCommand(SetVendorContractDocumentParameter request) {
            this.contractHistoryId = Long.valueOf(request.getVendorContractID());
            this.typeCode = request.getDocumentTypeID();
            this.fileName1 = request.getFileName();
            this.fileName2 = request.getFileName2();
            this.fileName3 = request.getFileName3();
            this.note = request.getNote();
            this.receivedBy = request.getReceivedBy();
            this.documentId = Long.valueOf(request.getVendorContractDocumentID());
        }

        private ContractDocumentCommand(List<Long> documentIds) {
            this.documentIds = documentIds;
        }

    }

}
