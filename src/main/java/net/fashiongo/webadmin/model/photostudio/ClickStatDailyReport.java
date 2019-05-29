package net.fashiongo.webadmin.model.photostudio;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.boot.Banner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@Accessors(chain = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class ClickStatDailyReport {

    private Integer wholeSalerId;

    private String wholeSalerCompanyName;

    @Getter(AccessLevel.PRIVATE)
    private Map<BannerType, Integer> clickCountMap = new HashMap<>();

    public Integer getClickCount(BannerType bannerType) {
        return Optional.ofNullable(clickCountMap.get(bannerType)).orElse(0);
    }

    private void setBannerClickStatistic(PhotoBannerClickStatistic photoBannerClickStatistic) {
        BannerType bannerType = BannerType.get(photoBannerClickStatistic.getBannerType().intValue());
        clickCountMap.put(bannerType, photoBannerClickStatistic.getBannerClickCount());
    }

    public static List<ClickStatDailyReport> build(List<PhotoBannerClickStatistic> photoBannerClickStatistics) {

        Map<Integer, ClickStatDailyReport> result = new HashMap<>();

        for(PhotoBannerClickStatistic photoBannerClickStatistic : photoBannerClickStatistics) {

            ClickStatDailyReport clickStatDailyReport = null;
            if(result.containsKey(photoBannerClickStatistic.getWholeSalerId())) {
                clickStatDailyReport = result.get(photoBannerClickStatistic.getWholeSalerId());
            } else {
                clickStatDailyReport = new ClickStatDailyReport().toBuilder().build();
            }
            clickStatDailyReport.setWholeSalerId(photoBannerClickStatistic.getWholeSalerId())
                .setWholeSalerCompanyName(photoBannerClickStatistic.getWholeSalerCompanyName())
                .setBannerClickStatistic(photoBannerClickStatistic);

            result.put(photoBannerClickStatistic.getWholeSalerId(), clickStatDailyReport);
        }

        return result.values().stream().collect(Collectors.toList());
    }
}
