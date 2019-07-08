package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SecurityAccessCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityAccessCodeEntityRepository extends JpaRepository<SecurityAccessCodeEntity, Integer>, SecurityAccessCodeEntityRepositoryCustom {
}
