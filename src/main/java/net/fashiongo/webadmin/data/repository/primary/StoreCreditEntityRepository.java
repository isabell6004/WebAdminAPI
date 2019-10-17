package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.StoreCreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCreditEntityRepository extends JpaRepository<StoreCreditEntity,Integer>, StoreCreditEntityRepositoryCustom {
}
