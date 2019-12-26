package net.fashiongo.webadmin.service.renewal;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.fashiongo.webadmin.data.model.ad.BidAdPage;
import net.fashiongo.webadmin.data.model.common.CodeOrderStatus;
import net.fashiongo.webadmin.data.model.common.SubCategories;
import net.fashiongo.webadmin.data.model.common.VendorsCompanyName;
import net.fashiongo.webadmin.data.model.common.reponse.ConsolidationOrderStatusResponse;
import net.fashiongo.webadmin.data.model.common.reponse.GetVendorsResponse;
import net.fashiongo.webadmin.data.model.vendor.SendVendorEmailParamter;
import net.fashiongo.webadmin.data.repository.primary.AdPageEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.CategoryEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.CodeOrderStatusEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenewalCommonService {
    @Autowired
    private final AdPageEntityRepository adPageEntityRepository;

    @Autowired
    private final CodeOrderStatusEntityRepository codeOrderStatusEntityRepository;

    @Autowired
    private final VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;

    @Autowired
    private final CategoryEntityRepository categoryEntityRepository;

    @Autowired
    @Qualifier("serviceJsonClient")
    private HttpClient jsonClient;

    public RenewalCommonService(AdPageEntityRepository adPageEntityRepository, CodeOrderStatusEntityRepository codeOrderStatusEntityRepository, VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository, CategoryEntityRepository categoryEntityRepository) {
        this.adPageEntityRepository = adPageEntityRepository;
        this.codeOrderStatusEntityRepository = codeOrderStatusEntityRepository;
        this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
        this.categoryEntityRepository = categoryEntityRepository;
    }

    public net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse getBidAdPages() {
        net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse result = new net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse();

        List<BidAdPage> _result = adPageEntityRepository.up_wa_GetBidAdPages();
        result.setBidAdPage(_result);

        return result;
    }

	public JsonResponse getCountries() {
        return jsonClient.get("/location/countries");
    }

    public List<CodeOrderStatus> getOrderstatus() {
        return codeOrderStatusEntityRepository.findAll().stream()
                .map(codeOrderStatusEntity ->
                        CodeOrderStatus.builder()
                                .orderStatusID(codeOrderStatusEntity.getOrderStatusID())
                                .orderStatusName(codeOrderStatusEntity.getOrderStatusName())
                                .name2Vendor(codeOrderStatusEntity.getName2Vendor())
                                .name2Retailer(codeOrderStatusEntity.getName2Retailer())
                        .build()
                ).collect(Collectors.toList());

    }

    public JsonResponse sendEmail(String title, String sender, String senderName, String recipient, String recipientName, String message) {
        JsonResponse response = new JsonResponse(false, null, null);

        SendVendorEmailParamter param = new SendVendorEmailParamter();
        param.setMessage(message);
        param.setRecipientEmailAddress(recipient);
        param.setRecipientName(recipientName);
        param.setSenderEmailAddress(sender);
        param.setSenderName(senderName);
        param.setSubject(title);

        try {
            response = jsonClient.post("/email/sendEmail", new ObjectMapper().writeValueAsString(param));
            response.setData(1);
        } catch (Exception e) {
            response.setData(-1);
        }

        return response;
    }

    public ConsolidationOrderStatusResponse getConsolidationOrderStatus() {
        List<CodeOrderStatus> codeOrderStatuses = codeOrderStatusEntityRepository.finAllConsolidationOrders();
        return ConsolidationOrderStatusResponse.builder()
                .consolidationOrderStatus(codeOrderStatuses)
                .build();
    }

    public GetVendorsResponse getVendors() {
        List<VendorsCompanyName> vendorsCompanyNames = vendorWholeSalerEntityRepository.findVendors();
        return GetVendorsResponse.builder()
                .vendorsCompanyNames(vendorsCompanyNames)
                .build();
    }

    public List<SubCategories> getSubCategories(Integer lvl, Integer parentCateId) {
        return categoryEntityRepository.findAllByLvlAndParentCateID(lvl,parentCateId);
    }
}
