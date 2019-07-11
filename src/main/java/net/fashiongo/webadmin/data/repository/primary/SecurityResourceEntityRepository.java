package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SecurityResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityResourceEntityRepository extends JpaRepository<SecurityResourceEntity,Integer>, SecurityResourceEntityRepositoryCustom {
}
