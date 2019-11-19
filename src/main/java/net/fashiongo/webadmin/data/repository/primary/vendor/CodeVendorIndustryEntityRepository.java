package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.CodeVendorIndustryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeVendorIndustryEntityRepository extends JpaRepository<CodeVendorIndustryEntity, Integer>, CodeVendorIndustryEntityRepositoryCustom {
}
