package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeVendorCapTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeVendorCapTypeEntityRepository extends JpaRepository<CodeVendorCapTypeEntity, Integer>, CodeVendorCapTypeEntityRepositoryCustom {
}
