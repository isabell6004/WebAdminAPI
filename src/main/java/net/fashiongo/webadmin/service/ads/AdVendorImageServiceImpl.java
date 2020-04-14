package net.fashiongo.webadmin.service.ads;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorImageRequestEntityRepository;
import net.fashiongo.webadmin.model.ads.response.VerifyVendorImageResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdVendorImageServiceImpl implements AdVendorImageService {

    private VendorImageRequestEntityRepository vendorImageRequestEntityRepository;

    public AdVendorImageServiceImpl(VendorImageRequestEntityRepository vendorImageRequestEntityRepository) {
        this.vendorImageRequestEntityRepository = vendorImageRequestEntityRepository;
    }

    @Override
    public List<VerifyVendorImageResponse> getVerifyVendorImages(List<Integer> vendorIds, List<Integer> vendorImageTypes) {

        if (CollectionUtils.isEmpty(vendorIds) || CollectionUtils.isEmpty(vendorImageTypes)) {
            log.warn("Invalid Requests. vendorIds and vendorImageTypes can not be empty.");
            throw new IllegalArgumentException("Invalid Request.");
        }

        Map<Integer, Map<Integer, List<VendorImageRequestEntity>>> vendorIdMap = vendorImageRequestEntityRepository
                .findByWholesalerIdInAndVendorImageTypeIdIn(vendorIds, vendorImageTypes)
                .stream()
                .collect(
                        Collectors.groupingBy(
                                VendorImageRequestEntity::getWholesalerId,
                                Collectors.groupingBy(
                                        VendorImageRequestEntity::getVendorImageTypeId,
                                        Collectors.mapping(Function.identity(), Collectors.toList())
                                )
                        )
                );

        List<VerifyVendorImageResponse> responses = new ArrayList<>();

        vendorIds.forEach(vendorId -> {

            vendorImageTypes.forEach(vendorImageType -> {

                if (!vendorIdMap.containsKey(vendorId))
                    responses.add(VerifyVendorImageResponse.of(vendorId, vendorImageType, Boolean.FALSE, Boolean.FALSE));
                else if (!vendorIdMap.get(vendorId).containsKey(vendorImageType))
                    responses.add(VerifyVendorImageResponse.of(vendorId, vendorImageType, Boolean.FALSE, Boolean.FALSE));
                else
                    responses.add(VerifyVendorImageResponse.from(vendorIdMap.get(vendorId).get(vendorImageType)));
            });
        });

        return responses;
    }
}
