package net.fashiongo.webadmin.data.model.vendor;

import lombok.*;
import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerGroupEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VendorSimilarDto {
    private Long id;
    private Long similarVendorId;

    public static VendorSimilarDto create(MapWholeSalerGroupEntity mapWholeSalerGroupEntity) {
        return builder()
                .id(Long.valueOf(mapWholeSalerGroupEntity.getMapID()))
                .similarVendorId(Long.valueOf(mapWholeSalerGroupEntity.getWholeSalerID2()))
                .build();
    }

    public static List<VendorSimilarDto> create(List<MapWholeSalerGroupEntity> mapWholeSalerGroupEntityList) {
        if (CollectionUtils.isEmpty(mapWholeSalerGroupEntityList))
            return Collections.EMPTY_LIST;

        return mapWholeSalerGroupEntityList.stream()
                .map(VendorSimilarDto::create)
                .collect(Collectors.toList());
    }
}
