package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.CodeVendorIndustryEntity;

import java.util.List;

public interface CodeVendorIndustryEntityRepositoryCustom {
    List<CodeVendorIndustryEntity> findAllCodeVendorIndustriesOrderById();
}
