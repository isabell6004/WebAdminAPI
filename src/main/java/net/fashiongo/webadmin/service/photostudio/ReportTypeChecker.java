package net.fashiongo.webadmin.service.photostudio;

/**
 * Created by jinwoo on 2019. 4. 29..
 */

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.photostudio.PhotoStudioProperties;
import net.fashiongo.webadmin.model.photostudio.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReportTypeChecker {

    private static PhotoStudioProperties photoStudioProperties;

    @Autowired
    public ReportTypeChecker(PhotoStudioProperties photoStudioProperties) {
        this.photoStudioProperties = photoStudioProperties;
    }

    public static Boolean checkReportType(ReportType type, Integer reqCartegoryId, Integer reqPackageId) {
        List<Integer> originCategoryIds = Optional.ofNullable(photoStudioProperties.getCategoryIds(type)).orElse(new ArrayList<>());
        List<Integer> originPackageIds = Optional.ofNullable(photoStudioProperties.getPackageIds(type)).orElse(new ArrayList<>());

        if (originCategoryIds.contains(reqCartegoryId) && (reqPackageId == null || originPackageIds.contains(reqPackageId))) {
            return true;
        }
        return false;
    }

    public static Boolean isFullModelShotWoman(Integer reqCategoryId, Integer reqPackageId) {
        return checkReportType(ReportType.FullModelShotWomen, reqCategoryId, reqPackageId);
    }

    public static Boolean isFullModelShotWomanPlusSize(Integer reqCategoryId, Integer reqPackageId) {
        return checkReportType(ReportType.FullModelShotWomenPlusSize, reqCategoryId, reqPackageId);
    }

    public static Boolean isFlatProductShotMan(Integer reqCategoryId, Integer reqPackageId) {
        return checkReportType(ReportType.FlatProductShotMen, reqCategoryId, reqPackageId);
    }

    public static Boolean isFlatProductShotKids(Integer reqCategoryId, Integer reqPackageId) {
        return checkReportType(ReportType.FlatProductShotKids, reqCategoryId, reqPackageId);
    }

    public static Boolean isFlatProductShotShoes(Integer reqCategoryId, Integer reqPackageId) {
        return checkReportType(ReportType.FlatProductShotShoes, reqCategoryId, reqPackageId);
    }

    public static Boolean isFlatProductShotHandbags(Integer reqCategoryId, Integer reqPackageId) {
        return checkReportType(ReportType.FlatProductShotHandbags, reqCategoryId, reqPackageId);
    }

    public static Boolean isFlatProductShotAccessories(Integer reqCategoryId, Integer reqPackageId) {
        return checkReportType(ReportType.FlatProductShotAccessories, reqCategoryId, reqPackageId);
    }
}
