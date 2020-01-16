package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.service.FashionGoApiConfig;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2019-12-12.
 */
@Service
@Slf4j
public class VendorContractNewServiceImpl implements VendorContractNewService {

    private HttpClientWrapper httpCaller;

    public VendorContractNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void createVendorContractDocument(SetVendorContractDocumentParameter request) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/contract/document";
        ContractDocumentCommand newRequest = new ContractDocumentCommand(request);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader());
    }

    @Override
    public void modifyVendorContractDocument(SetVendorContractDocumentParameter request) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/contract/document";
        ContractDocumentCommand newRequest = new ContractDocumentCommand(request);
        httpCaller.put(endpoint, newRequest, FashionGoApiHeader.getHeader());
    }

    @Override
    public void deleteVendorContractDocument(List<Long> documentIds) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/contract/document/delete";
        ContractDocumentCommand newRequest = new ContractDocumentCommand(documentIds);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader());
    }

    @Override
    public void createAndModifyVendorContractHistory(SetVendorContractParameter request) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/contract";
        log.debug("call the vendor api:{}", endpoint);
        ContractHistoryCommand newRequest = new ContractHistoryCommand(request);
        httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader());
    }

    @Getter
    private class ContractHistoryCommand {

        private Long contractHistoryId;

        private Integer vendorId;
        //    private Integer typeCode;
        private Long planId;

        private BigDecimal setupFee;
        private Boolean isSetupFeeWaived;

        private BigDecimal lastMonthServiceFee;
        private Boolean isLastMonthServiceFeeWaived;
        private BigDecimal monthlyFee;
        private Integer monthlyItemCap;

        private Integer accountExecutiveId;
        private String accountExecutiveBy;

        private BigDecimal commissionRate;
        private String dateFrom;
//    private LocalDateTime dateTo;

        private String memo;
        private Boolean isContractRevised;

        private ContractHistoryCommand(SetVendorContractParameter request) {
            this.vendorId = request.getWholeSalerID();
            this.contractHistoryId = Long.valueOf(request.getVendorContractID());
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
//            this.dateTo = Timestamp.valueOf(SetVendorContractParameter.getVendorContractFrom(request.getVendorContractFrom()).toLocalDateTime().minusDays(1)).toLocalDateTime();
            this.memo = request.getMemo();
            this.isContractRevised = request.getVendorContractRowAdd();
        }
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
