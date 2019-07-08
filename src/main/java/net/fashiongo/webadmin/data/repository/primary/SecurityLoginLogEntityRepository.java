package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SecurityLoginLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityLoginLogEntityRepository extends JpaRepository<SecurityLoginLogEntity, Integer>, SecurityLoginLogEntityRepositoryCustom {
}
