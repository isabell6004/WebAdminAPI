package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorContractDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorContractDocumentEntityRepository extends JpaRepository<VendorContractDocumentEntity,Integer>, VendorContractDocumentEntityRepositoryCustom {
}
