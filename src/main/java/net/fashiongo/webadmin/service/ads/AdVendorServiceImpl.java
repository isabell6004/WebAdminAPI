package net.fashiongo.webadmin.service.ads;

import net.fashiongo.webadmin.data.repository.primary.WholeSalerEntityRepository;
import net.fashiongo.webadmin.model.ads.response.AdVendorResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdVendorServiceImpl implements AdVendorService {

    private WholeSalerEntityRepository wholeSalerEntityRepository;

    public AdVendorServiceImpl(WholeSalerEntityRepository wholeSalerEntityRepository) {
        this.wholeSalerEntityRepository = wholeSalerEntityRepository;
    }

    @Override
    public List<AdVendorResponse> getVendorNames(Boolean shopActive, Boolean orderActive, List<Integer> vendorIds) {

        return wholeSalerEntityRepository.findAllActiveWholesalers(shopActive, orderActive, vendorIds).stream()
                .map(AdVendorResponse::from)
                .collect(Collectors.toList());
    }
}
