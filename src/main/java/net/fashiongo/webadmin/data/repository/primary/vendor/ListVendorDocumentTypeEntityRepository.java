package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.ListVendorDocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListVendorDocumentTypeEntityRepository extends JpaRepository<ListVendorDocumentTypeEntity, Integer>, ListVendorDocumentTypeEntityRepositoryCustom {
}
