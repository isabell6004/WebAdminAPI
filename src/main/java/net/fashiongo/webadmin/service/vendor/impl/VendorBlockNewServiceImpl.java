package net.fashiongo.webadmin.service.vendor.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.display.response.DisplaySettingResponse;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorBlockAdParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorBlockAdminLoginParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorBlockPayoutParameter;
import net.fashiongo.webadmin.data.model.vendor.response.CodeVendorBlockReasonResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockHistoryResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockPayoutScheduleInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockResponse;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.PaymentScheduleInfo;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorUnBlockParameter;
import net.fashiongo.webadmin.service.PaymentService;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
import net.fashiongo.webadmin.service.vendor.VendorBlockNewService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class VendorBlockNewServiceImpl implements VendorBlockNewService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final static String Vendor_Request_Command_Key_Name = "setting";

    private HttpClientWrapper httpCaller;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private PaymentService paymentService;

    public VendorBlockNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    private Map<String, String> getHeader() {
        WebAdminLoginUser userInfo = Utility.getUserInfo();
        return FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername());
    }

    private <T> T resolveResponse(FashionGoApiResponse<T> response) {
        if (response == null) {
            throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
        }

        if (response.getHeader().isSuccessful()) {
            return response.getData();
        } else {
            throw new RuntimeException("error: " + response.getHeader().getResultMessage());
        }
    }


    @Override
    public CollectionObject<VendorBlockResponse> getVendorBlockList(GetVendorBlockListParameter parameter) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/block-vendors";

        Long vendorId = null;
        String companyName = null;
        LocalDate blockDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (parameter.getSearchType().equals("VendorID") ) {
            vendorId = Long.parseLong(parameter.getSearchKeyword());
        }
        if (parameter.getSearchType().equals("Company") ) {
            companyName = parameter.getSearchKeyword();
        }
        if (parameter.getSearchType().equals("Date") ) {
            blockDate = LocalDate.parse(parameter.getSearchKeyword(), formatter);
        }

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("vendorId", vendorId)
                .queryParam("companyName", companyName)
                .queryParam("blockDate", blockDate)
                .queryParam("reasonId", null)
                .queryParam("typeCode", parameter.getSearchTypeCode())
                .queryParam("pn", parameter.getPageNum())
                .queryParam("ps", parameter.getPageSize())
                .build(false);

        FashionGoApiResponse<CollectionObject<VendorBlockResponse>> response = httpCaller.get(builder.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<VendorBlockResponse>>>() {});

        return resolveResponse(response);
    }

    @Override
    public CollectionObject<VendorBlockHistoryResponse> getVendorBlockHistoryList(Long vendorId) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/block-history";

        Integer typeCode = null;

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("typeCode", typeCode)
                .build(false);

        FashionGoApiResponse<CollectionObject<VendorBlockHistoryResponse>> response = httpCaller.get(builder.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<VendorBlockHistoryResponse>>>() {});

        return resolveResponse(response);
    }

    @Override
    public List<CodeVendorBlockReasonResponse> getCodeVendorBlockReason(Long vendorId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/block-reasons";
        boolean isActive = true;
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("isActive", isActive)
                .build(false);

        FashionGoApiResponse<CollectionObject<CodeVendorBlockReasonResponse>> response = httpCaller.get(builder.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CodeVendorBlockReasonResponse>>>() {});
        return resolveResponse(response).getContents();
    }


    @Override
    public Boolean updatePayoutBlock(VendorBlockPayoutParameter param) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + param.getVendorId();
        VendorSettingCommand<VendorSettingPayoutBlockCommand> vendorSettingCommand = new VendorSettingCommand<>(new VendorSettingPayoutBlockCommand(param.getIsPayoutBlock(),param.getPayoutBlockReasonId()));
        String responseBody = httpCaller.put(endpoint, vendorSettingCommand, getHeader());

        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            if (response == null) {
                throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
            }
            return response.getHeader().isSuccessful();
        } catch (IOException e) {
            throw new RuntimeException("fail to update vendor payout block. " + e.getMessage());
        }
    }

    @Override
    public Boolean updateBlock(VendorBlockAdminLoginParameter param) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + param.getVendorId();
        VendorSettingCommand<VendorSettingBlockCommand> vendorInfoCommand = new VendorSettingCommand<>(new VendorSettingBlockCommand(param.getIsBlock(),param.getBlockReasonId()));
        String responseBody = httpCaller.put(endpoint, vendorInfoCommand, getHeader());

        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            if (response == null) {
                throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
            }
            return response.getHeader().isSuccessful();
        } catch (IOException e) {
            throw new RuntimeException("fail to update vendor admin login block. " + e.getMessage());
        }
    }

    @Override
    public Boolean updateAdBlock(VendorBlockAdParameter param) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + param.getVendorId();
        VendorSettingCommand<VendorSettingAdBlockCommand> vendorInfoCommand = new VendorSettingCommand<>(new VendorSettingAdBlockCommand(param.getIsAdBlock(),param.getAdBlockReasonId()));
        String responseBody = httpCaller.put(endpoint, vendorInfoCommand, getHeader());

        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            if (response == null) {
                throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
            }
            return response.getHeader().isSuccessful();
        } catch (IOException e) {
            throw new RuntimeException("fail to update vendor Ad block. " + e.getMessage());
        }
    }

    @Override
    public VendorBlockPayoutScheduleInfoResponse getVendorPreviousPayoutScheduleInfo(Long vendorId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/previous-payout-schedule-info";

        FashionGoApiResponse<SingleObject<VendorBlockPayoutScheduleInfoResponse>> response = httpCaller.get(endpoint, getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<VendorBlockPayoutScheduleInfoResponse>>>() {});
        return resolveResponse(response).getContent();
    }

    @Getter
    private class VendorSettingCommand<T> {
        private T setting;
        private VendorSettingCommand(T setting) {
            this.setting = setting;
        }
    }

    @Getter
    private class VendorSettingBlockCommand {
        private Boolean isBlock;
        private Long blockReasonId;
        private VendorSettingBlockCommand(Boolean isBlock, Long blockReasonId) {
            this.isBlock = isBlock;
            this.blockReasonId = blockReasonId;
        }
    }

    @Getter
    private class VendorSettingAdBlockCommand {
        private Boolean isAdBlock;
        private Long adBlockReasonId;
        private VendorSettingAdBlockCommand(Boolean isAdBlock, Long adBlockReasonId) {
            this.isAdBlock = isAdBlock;
            this.adBlockReasonId = adBlockReasonId;
        }
    }

    @Getter
    private class VendorSettingPayoutBlockCommand {
        private Boolean isPayoutBlock;
        private Long payoutBlockReasonId;
        private VendorSettingPayoutBlockCommand(Boolean isPayoutBlock, Long payoutBlockReasonId) {
            this.isPayoutBlock = isPayoutBlock;
            this.payoutBlockReasonId = payoutBlockReasonId;
        }
    }
}
