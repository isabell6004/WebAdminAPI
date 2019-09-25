package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorContractEntityRepository extends JpaRepository<VendorContractEntity,Integer> , VendorContractEntityRepositoryCustom {
}
