package net.fashiongo.webadmin.data.model.sitemgmt;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResultGetCategoryVendorList {
    private List<CategoryCount> categoryCountlist;

    private List<CategoryVendor> categoryVendorList;

    private List<CategoryVendorInfo> categoryVendorInfoList;
}
