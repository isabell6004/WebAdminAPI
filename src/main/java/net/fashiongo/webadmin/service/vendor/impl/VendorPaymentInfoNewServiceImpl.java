package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.AccountType;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSNSListParameter;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.VendorPaymentInfoNewService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VendorPaymentInfoNewServiceImpl implements VendorPaymentInfoNewService {

    private HttpClientWrapper httpCaller;

    public VendorPaymentInfoNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void switchPaymentMethodId(Integer wholeSalerID, Integer oldPaymentMethodId, Integer newPaymentMethodId, Integer requestUserId, String requestUserName) {
        deletePaymentMethod(wholeSalerID, oldPaymentMethodId, requestUserId, requestUserName);
        registerPaymentMethod(wholeSalerID, newPaymentMethodId, requestUserId, requestUserName);
    }

    private void registerPaymentMethod(Integer wholeSalerID, Integer paymentMethodId, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + wholeSalerID + "/payment-methods";
        VendorPaymentMethodCommand vendorPaymentMethodCommand = new VendorPaymentMethodCommand(paymentMethodId);
        httpCaller.post(endpoint, vendorPaymentMethodCommand, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    private void deletePaymentMethod(Integer wholeSalerID, Integer paymentMethodId, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + wholeSalerID + "/payment-methods/" + paymentMethodId;
        httpCaller.delete(endpoint, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Getter
    private class VendorPaymentMethodCommand {
        private Integer paymentMethodId;

        VendorPaymentMethodCommand(Integer paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
        }
    }
}
