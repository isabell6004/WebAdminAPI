package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.ad.BidAdPage;
import net.fashiongo.webadmin.data.repository.primary.AdPageEntityRepository;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenewalCommonService {
    @Autowired
    private final AdPageEntityRepository adPageEntityRepository;

    @Autowired
    @Qualifier("serviceJsonClient")
    private HttpClient jsonClient;

    public RenewalCommonService(AdPageEntityRepository adPageEntityRepository) {
        this.adPageEntityRepository = adPageEntityRepository;
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
}
