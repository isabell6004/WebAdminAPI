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
import java.util.Optional;
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

        Map<Integer, Map<Integer, Boolean>> vendorIdMap = vendorImageRequestEntityRepository.findByWholesalerIdInAndVendorImageTypeIdInAndActive(
                vendorIds, vendorImageTypes, Boolean.TRUE
        ).stream()
                .collect(
                        Collectors.groupingBy(
                                VendorImageRequestEntity::getWholesalerId,
                                Collectors.groupingBy(
                                        VendorImageRequestEntity::getVendorImageTypeId,
                                        Collectors.reducing(
                                                Boolean.FALSE,
                                                vendorImageRequest -> Optional.ofNullable(vendorImageRequest.getIsApproved()).orElse(Boolean.FALSE),
                                                Boolean::logicalOr)
                                )
                        )
                );

        List<VerifyVendorImageResponse> responses = new ArrayList<>();

        vendorIds.forEach(vendorId -> {
            boolean hasVendorId = vendorIdMap.containsKey(vendorId);

            vendorImageTypes.forEach(vendorImageType -> {
                if (hasVendorId)
                    responses.add(VerifyVendorImageResponse.of(
                            vendorId,
                            vendorImageType,
                            Optional.ofNullable(vendorIdMap.get(vendorId)
                                    .get(vendorImageType))
                                    .orElse(Boolean.FALSE)
                    ));
                else
                    responses.add(VerifyVendorImageResponse.unapproved(vendorId, vendorImageType));
            });
        });

        return responses;
    }
}
