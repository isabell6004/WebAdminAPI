package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.MapFranchiseSubAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapFranchiseSubAccountEntityRepository extends JpaRepository<MapFranchiseSubAccountEntity,Integer>, MapFranchiseSubAccountEntityRepositoryCustom {
}
