package net.fashiongo.webadmin.service.vendor.impl;

import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerGroupEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingSelected;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingUnselect;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorGroupingResponse;
import net.fashiongo.webadmin.data.repository.primary.MapWholeSalerGroupEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import net.fashiongo.webadmin.service.vendor.SimilarVendorNewService;
import net.fashiongo.webadmin.service.vendor.SimilarVendorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SimilarVendorServiceImpl implements SimilarVendorService {

    private final VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;
    private final MapWholeSalerGroupEntityRepository mapWholeSalerGroupEntityRepository;
    private final SimilarVendorNewService similarVendorNewService;

    public SimilarVendorServiceImpl(VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository,
                                    MapWholeSalerGroupEntityRepository mapWholeSalerGroupEntityRepository,
                                    SimilarVendorNewService similarVendorNewService) {
        this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
        this.mapWholeSalerGroupEntityRepository = mapWholeSalerGroupEntityRepository;
        this.similarVendorNewService = similarVendorNewService;
    }

    @Override
    @Transactional
    public GetVendorGroupingResponse getVendorGroupings(Integer vendorId,
                                                       String companyType,
                                                       String vendorType,
                                                       String keyword,
                                                       String categorys,
                                                       String alphabet) {
        vendorId = vendorId == null ? 0 : vendorId;
        companyType = StringUtils.isEmpty(companyType) ? "" : companyType;
        vendorType = StringUtils.isEmpty(vendorType) ? "" : vendorType;
        keyword = StringUtils.isEmpty(keyword) ? " " : keyword;
        alphabet = StringUtils.isEmpty(alphabet) ? "" : alphabet;

        categorys = StringUtils.isEmpty(categorys) ? "" : categorys;
        String[] categoryList = categorys.replace("'", "").split(",");

        ArrayList<Integer> categoryIntegerList = new ArrayList<>();
        for (String t : categoryList) {
            if (StringUtils.isNotEmpty(t)) {
                categoryIntegerList.add(Integer.valueOf(t));
            }
        }

        if (vendorType.equalsIgnoreCase("email")) {
            vendorType = vendorType.toLowerCase();
        } else {
            vendorType = vendorType.substring(0, 1).toLowerCase() + vendorType.substring(1);
        }

        List<VendorGroupingSelected> vendorGroupingSelectedList = vendorWholeSalerEntityRepository.findListVendorGroupingSelect(vendorId, null, keyword, categoryIntegerList, alphabet, vendorType);
        List<VendorGroupingUnselect> vendorGroupingUnselectList = vendorWholeSalerEntityRepository.findListVendorGroupingUnSelect(vendorId, null, keyword, categoryIntegerList, alphabet, vendorType);

        return GetVendorGroupingResponse.builder()
                .vendorGroupingSelete(vendorGroupingSelectedList)
                .vendorGroupingUnSelete(vendorGroupingUnselectList)
                .build();
    }

    @Override
    @Transactional
    public Integer setVendorGrouping(Integer vendorId, String saveIds, String deleteIds) {
        Integer result = null;

        if (StringUtils.isEmpty(saveIds) && StringUtils.isEmpty(deleteIds))
            return null;

        if (StringUtils.isNotEmpty(deleteIds)) {
            String[] deleteIDList = deleteIds.split(",");
            ArrayList<Integer> widList = new ArrayList<>();
            for (String id : deleteIDList) {
                if (StringUtils.isNotEmpty(id))
                    widList.add(Integer.valueOf(id));
            }
            List<MapWholeSalerGroupEntity> deleteEntities = mapWholeSalerGroupEntityRepository.findAllByIds(widList);

            mapWholeSalerGroupEntityRepository.deleteAll(deleteEntities);

            // TODO: call fashiongo API - done
            similarVendorNewService.deleteSimilarVendor(vendorId, widList);

            result = 1;
        }

        if (StringUtils.isNotEmpty(saveIds)) {
            String[] saveIDList = saveIds.split(",");
            List<Integer> intIdList = Arrays.stream(saveIDList).map(Integer::valueOf).collect(Collectors.toList());

            List<MapWholeSalerGroupEntity> insertEntities = new ArrayList<>();
            for (String id : saveIDList) {
                if (StringUtils.isNotEmpty(id)) {
                    MapWholeSalerGroupEntity WG = new MapWholeSalerGroupEntity();
                    WG.setWholeSalerID(vendorId);
                    WG.setWholeSalerID2(Integer.valueOf(id));

                    insertEntities.add(WG);
                }
            }

            mapWholeSalerGroupEntityRepository.saveAll(insertEntities);

            // TODO: call fashiongo API - done
            similarVendorNewService.addSimilarVendor(vendorId, intIdList);

            result = 1;
        }

        return result;
    }
}
