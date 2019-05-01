package net.fashiongo.webadmin.model.photostudio;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2019. 4. 25..
 */
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "photostudio")
@Getter
@Setter
public class PhotoStudioProperties {

    @Getter(AccessLevel.PRIVATE)
    private ReportTypeProperties reportTypeProperties;

    @Getter(AccessLevel.PRIVATE)
    private Map<String, List<Integer>> fullModelPackageIdMap;

    public List<Integer> getCategoryIds(ReportType value) {
        return Optional.ofNullable(reportTypeProperties.getCategoryIdMap().get(value.name())).orElse(new ArrayList<>());
    }

    public List<Integer> getPackageIds(ReportType value) {
        return Optional.ofNullable(reportTypeProperties.getPackageIdMap().get(value.name())).orElse(new ArrayList<>());
    }

    public List<Integer> getFullModelPackageIds(String value) {
        return Optional.ofNullable(fullModelPackageIdMap.get(value)).orElse(new ArrayList<>());
    }
}
