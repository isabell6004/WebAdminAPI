package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.LogVendorHoldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogVendorHoldEntityRepository extends JpaRepository<LogVendorHoldEntity, Integer>, LogVendorHoldEntityRepositoryCustom {
}
