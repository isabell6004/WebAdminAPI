package net.fashiongo.webadmin.service.renewal.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.renewal.VendorContractNewService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jinwoo on 2019-12-12.
 */
@Service
@Slf4j
public class VendorContractNewServiceImpl implements VendorContractNewService {

    @Value("${api.endpoint.newVendorApi}")
    private String newVendorApi;

    private HttpClientWrapper httpCaller;

    public VendorContractNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public Boolean createAndModifyVendorContractDocument(SetVendorContractDocumentParameter request) {
        final String endpoint = newVendorApi + "/v1.0/contract/document";
        ContractDocumentCommand newRequest = new ContractDocumentCommand(request);
        return isApiCallResult(httpCaller.post(endpoint, newRequest, VendorApiHeader.getHeader()));
    }

    @Override
    public Boolean deleteVendorContractDocument(List<Long> documentIds) {
        final String endpoint = newVendorApi + "/v1.0/contract/document/delete";
        ContractDocumentCommand newRequest = new ContractDocumentCommand(documentIds);
        return isApiCallResult(httpCaller.post(endpoint, newRequest, VendorApiHeader.getHeader()));
    }

    private boolean isApiCallResult(ResponseEntity<JsonResponse> response) {
        if(response == null || response.getStatusCode() != HttpStatus.OK || response.getBody() == null)
            return false;
        else
            return response.getBody().isSuccess();
    }

    @Getter
    private class ContractDocumentCommand {
        private Integer typeCode;
        private String fileName1;
        private String fileName2;
        private String fileName3;
        private String note;
        private Integer receivedId;
        private String receivedBy;
        private Long documentId;
        private Long contractHistoryId;

        private List<Long> documentIds;

        private ContractDocumentCommand(SetVendorContractDocumentParameter request) {
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
