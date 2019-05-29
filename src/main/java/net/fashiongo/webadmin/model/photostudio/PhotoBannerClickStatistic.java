package net.fashiongo.webadmin.model.photostudio;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class PhotoBannerClickStatistic implements Serializable {

    private Integer wholeSalerId;

    private String wholeSalerCompanyName;

    private Integer bannerType;

    private Integer bannerClickCount = 0;

    public static List<PhotoBannerClickStatistic> build(List<Tuple> results) {
        return results.stream().map((tuple) -> new PhotoBannerClickStatistic().toBuilder()
                .wholeSalerId(tuple.get("wholeSalerId", Integer.class))
                .wholeSalerCompanyName(tuple.get("wholeSalerCompanyName", String.class))
                .bannerType(tuple.get("bannerType", Integer.class))
                .bannerClickCount(tuple.get("count", Long.class).intValue())
                .build()).collect(Collectors.toList());
    }
}
