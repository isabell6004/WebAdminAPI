package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.WholeRetailerBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WholeRetailerBlockEntityRepository extends JpaRepository<WholeRetailerBlockEntity,Integer>, WholeRetailerBlockEntityRepositoryCustom {
}
