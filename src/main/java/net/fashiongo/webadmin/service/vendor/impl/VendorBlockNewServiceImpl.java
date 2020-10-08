package net.fashiongo.webadmin.service.vendor.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.display.response.DisplaySettingResponse;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockParameter;
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
import java.util.Map;

@Service
@Slf4j
public class VendorBlockNewServiceImpl implements VendorBlockNewService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final static String Vendor_Request_Command_Key_Name = "setting";

    private HttpClientWrapper httpCaller;

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
    public void modifyBlockStatus(Integer wholeSalerId, Boolean isBlock, Long blockReasonId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + wholeSalerId;
        VendorInfoCommand command = new VendorInfoCommand(new VendorBlockStatusCommand(isBlock, blockReasonId));
        httpCaller.put(endpoint, command, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public void blockVendor(SetVendorBlockParameter request, Integer requestedUserId, String requestUserName) {
        modifyBlockStatus(request.getWholeSalerID(), true, Long.valueOf(request.getBlockReasonID()), requestedUserId, requestUserName);
    }

    @Override
    public void unblockVendor(DelVendorBlockParameter request, Integer requestedUserId, String requestUserName) {
        modifyBlockStatus(request.getWholeSalerID(), false, null, requestedUserId, requestUserName);
    }

    @Getter
    private class VendorInfoCommand<T> {
        private VendorBlockStatusCommand setting;
        private VendorInfoCommand(VendorBlockStatusCommand setting) {
            this.setting = setting;
        }
    }

    @Getter
    private class VendorBlockStatusCommand {
        private Boolean isBlock;
        private Long blockReasonId;

        private VendorBlockStatusCommand(Boolean isBlock, Long blockReasonId) {
            this.isBlock = isBlock;
            this.blockReasonId = blockReasonId;
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
    public Boolean modifyBlock(SetVendorUnBlockParameter param) {

        Long vendorId = (long)param.getWholeSalerID();
        Boolean result = false;
        Boolean result_payout = true;
        Boolean result_unblock = false;

        if (param.getTypeCode() == 3) {
            result_payout = paymentService.updatePaymentPayoutUnblock(param.getWholeSalerID());

            result = result_payout;
        }

        if (result_payout) {
            // call fashiongo api : Vendor unblock
            final String endpoint_setting = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId;

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> settingMap = new HashMap<>();
            if (param.getTypeCode() == 2) {
                settingMap.put("isAdBlock", false);
            }
            if (param.getTypeCode() == 3) {
                settingMap.put("isPayoutBlock", false);
                settingMap.put("isPayoutScheduleLock", false);
            }
            if (param.getTypeCode() == 1) {
                settingMap.put("isBlock", false);
            }
            Map<String, Object> payloadMap = new HashMap<>();
            payloadMap.put("setting", settingMap);

            String body = null;

            try {
                body = new ObjectMapper().writeValueAsString(payloadMap);
            } catch (IOException e) {
                logger.error("setPaymentAccountIsLocked()", e);
                result = false;
            }

            String responseBody = httpCaller.put(endpoint_setting, body, getHeader());
            try {
                FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {
                });

                result_unblock = response.getHeader().isSuccessful();
                result = result_unblock;

            } catch (IOException e) {
                logger.error("fail to update vendor unblock setting", e);
                result = false;
            }
        }

        return result;
    }
}
