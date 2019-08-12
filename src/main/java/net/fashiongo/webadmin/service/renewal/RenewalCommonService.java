package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.ad.BidAdPage;
import net.fashiongo.webadmin.data.repository.primary.AdPageEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenewalCommonService {
    @Autowired
    private final AdPageEntityRepository adPageEntityRepository;

    public RenewalCommonService(AdPageEntityRepository adPageEntityRepository) {
        this.adPageEntityRepository = adPageEntityRepository;
    }

    public net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse getBidAdPages() {
        net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse result = new net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse();

        List<BidAdPage> _result = adPageEntityRepository.up_wa_GetBidAdPages();
        result.setBidAdPage(_result);

        return result;
    }
}
