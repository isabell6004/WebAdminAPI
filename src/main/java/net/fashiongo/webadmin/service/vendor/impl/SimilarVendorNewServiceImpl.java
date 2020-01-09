package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.SimilarVendorNewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimilarVendorNewServiceImpl implements SimilarVendorNewService {

    @Value("${api.endpoint.newVendorApi}")
    private String newVendorApi;

    private final HttpClientWrapper httpCaller;

    public SimilarVendorNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void addSimilarVendor(Integer vendorId, List<Integer> similarVendorIdList) {
        final String endpoint = newVendorApi + "/v1.0/vendor/" + vendorId + "/similar";
        CreateSimilarVendorCommand newRequest = new CreateSimilarVendorCommand(similarVendorIdList);
        httpCaller.post(endpoint, newRequest, VendorApiHeader.getHeader());
    }

    @Override
    public void deleteSimilarVendor(Integer vendorId, List<Integer> mapIdList) {
        final String endpoint = newVendorApi + "/v1.0/vendor/" + vendorId + "/similar/delete";
        DeleteSimilarVendorCommand newRequest = new DeleteSimilarVendorCommand(mapIdList);
        httpCaller.post(endpoint, newRequest, VendorApiHeader.getHeader());
    }

    @Getter
    private class CreateSimilarVendorCommand {
        private List<Integer> similarVendorIds;

        private CreateSimilarVendorCommand(List<Integer> similarVendorIdList) {
            this.similarVendorIds = similarVendorIdList;
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
