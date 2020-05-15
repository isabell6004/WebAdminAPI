package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorContractHistoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface VendorContractHistoryEntityRepository extends CrudRepository<VendorContractHistoryEntity, Long> {
    VendorContractHistoryEntity findFirstByVendorIdOrderByIdDesc(Long vendorId);
}
