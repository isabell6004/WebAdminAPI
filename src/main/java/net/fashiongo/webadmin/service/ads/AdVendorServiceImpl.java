package net.fashiongo.webadmin.service.ads;

import net.fashiongo.webadmin.dao.primary.VendorEntityRepository;
import net.fashiongo.webadmin.model.ads.response.AdVendorResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdVendorServiceImpl implements AdVendorService {


    private VendorEntityRepository vendorEntityRepository;

    public AdVendorServiceImpl(VendorEntityRepository vendorEntityRepository) {
        this.vendorEntityRepository = vendorEntityRepository;
    }

    @Override
    public List<AdVendorResponse> getVendorNames(Boolean shopActive, Boolean orderActive, List<Integer> vendorIds) {
        return vendorEntityRepository.findAllActiveWholesalers(shopActive,orderActive,vendorIds).stream()
                .map(AdVendorResponse::from)
                .collect(Collectors.toList());
    }
}
