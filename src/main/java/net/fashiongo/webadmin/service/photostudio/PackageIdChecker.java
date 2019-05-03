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
public class PackageIdChecker {

    private static PhotoStudioProperties photoStudioProperties;

    @Autowired
    public PackageIdChecker(PhotoStudioProperties photoStudioProperties) {
        this.photoStudioProperties = photoStudioProperties;
    }

    public static Boolean checkFullModelPackage1(Integer reqPackageId) {
        return photoStudioProperties.getFullModelPackageIds("package1").contains(reqPackageId);
    }

    public static Boolean checkFullModelPackage2(Integer reqPackageId) {
        return photoStudioProperties.getFullModelPackageIds("package2").contains(reqPackageId);
    }

    public static Boolean checkFullModelPackage3(Integer reqPackageId) {
        return photoStudioProperties.getFullModelPackageIds("package3").contains(reqPackageId);
    }

}
