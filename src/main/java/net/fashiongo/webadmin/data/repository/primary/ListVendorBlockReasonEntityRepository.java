package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.ListVendorBlockReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListVendorBlockReasonEntityRepository extends JpaRepository<ListVendorBlockReasonEntity, Integer>, ListVendorBlockReasonEntityRepositoryCustom{
}
