package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorSimilarDto;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.SimilarVendorNewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimilarVendorNewServiceImpl implements SimilarVendorNewService {

    private final HttpClientWrapper httpCaller;

    public SimilarVendorNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void addSimilarVendor(Integer vendorId, List<VendorSimilarDto> similarVendorList, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/similar";
        CreateSimilarVendorCommand newRequest = new CreateSimilarVendorCommand(similarVendorList);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public void deleteSimilarVendor(Integer vendorId, List<Integer> mapIdList, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/similar/delete";
        DeleteSimilarVendorCommand newRequest = new DeleteSimilarVendorCommand(mapIdList);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Getter
    private class CreateSimilarVendorCommand {
        private List<VendorSimilarDto> similarVendors;

        private CreateSimilarVendorCommand(List<VendorSimilarDto> similarVendorList) {
            this.similarVendors = similarVendorList;
        }
    }

    @Getter
    private class DeleteSimilarVendorCommand {
        private List<Integer> vendorSimilarIds;

        private DeleteSimilarVendorCommand(List<Integer> mapIdList) {
            this.vendorSimilarIds = mapIdList;
        }
    }
}
