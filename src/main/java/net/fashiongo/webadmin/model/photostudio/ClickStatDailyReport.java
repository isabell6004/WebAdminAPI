package net.fashiongo.webadmin.model.photostudio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
public class ClickStatDailyReport {

    private Integer wholeSalerId;

    private String wholeSalerCompanyName;

    private Integer bannerClickCount = 0;

    private Integer menuClickCount = 0;

    private Integer closeOnTopCount = 0;

    private Integer closeForeverCount = 0;

    private Integer tryOutButtonCount = 0;

    public Integer getBannerClickCount() {
        return bannerClickCount;
    }

    public Integer getCloseForeverCount() {
        return closeForeverCount;
    }

    public void setCloseForeverCount(Integer closeForeverCount) {
        this.closeForeverCount = closeForeverCount;
    }

    public Integer getCloseOnTopCount() {
        return closeOnTopCount;
    }

    public void setCloseOnTopCount(Integer closeOnTopCount) {
        this.closeOnTopCount = closeOnTopCount;
    }

    public Integer getMenuClickCount() {
        return menuClickCount;
    }

    public void setMenuClickCount(Integer menuClickCount) {
        this.menuClickCount = menuClickCount;
    }

    public Integer getTryOutButtonCount() {
        return tryOutButtonCount;
    }

    public void setTryOutButtonCount(Integer tryOutButtonCount) {
        this.tryOutButtonCount = tryOutButtonCount;
    }

    public String getWholeSalerCompanyName() {
        return wholeSalerCompanyName;
    }

    public void setWholeSalerCompanyName(String wholeSalerCompanyName) {
        this.wholeSalerCompanyName = wholeSalerCompanyName;
    }

    public Integer getWholeSalerId() {
        return wholeSalerId;
    }

    public void setWholeSalerId(Integer wholeSalerId) {
        this.wholeSalerId = wholeSalerId;
    }

    public void setBannerClickCount(PhotoBannerClickStatistic photoBannerClickStatistic) {

//        if(photoBannerClickStatistic.getBannerType().intValue() == BannerType.AdsCloseButton.getValue()) {
//            this.setCloseOnTopCount(photoBannerClickStatistic.getBannerClickCount());
//        } else
         if (photoBannerClickStatistic.getBannerType().intValue() == BannerType.AdsDoNotShowAgain.getValue()) {
            this.setCloseForeverCount(photoBannerClickStatistic.getBannerClickCount());
        } else if (photoBannerClickStatistic.getBannerType().intValue() == BannerType.AdsTryBottom.getValue()) {
            this.setTryOutButtonCount(photoBannerClickStatistic.getBannerClickCount());
        }
//         else if (photoBannerClickStatistic.getBannerType().intValue() == BannerType.AdsTryTop.getValue()) {
//            this.setMenuClickCount(photoBannerClickStatistic.getBannerClickCount());
//        }
    }

    public static List<ClickStatDailyReport> build(List<PhotoBannerClickStatistic> photoBannerClickStatistics) {

        Map<Integer, ClickStatDailyReport> result = new HashMap<>();
        for(PhotoBannerClickStatistic photoBannerClickStatistic : photoBannerClickStatistics) {

            ClickStatDailyReport clickStatDailyReport = null;
            if(result.containsKey(photoBannerClickStatistic.getWholeSalerId())) {
                clickStatDailyReport = result.get(photoBannerClickStatistic.getWholeSalerId());
            } else {
                clickStatDailyReport = new ClickStatDailyReport();
            }
            clickStatDailyReport.setWholeSalerId(photoBannerClickStatistic.getWholeSalerId());
            clickStatDailyReport.setWholeSalerCompanyName(photoBannerClickStatistic.getWholeSalerCompanyName());
            clickStatDailyReport.setBannerClickCount(photoBannerClickStatistic);
            result.put(photoBannerClickStatistic.getWholeSalerId(), clickStatDailyReport);
        }

        return result.values().stream().collect(Collectors.toList());
    }
}
