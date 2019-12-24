package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SystemImageServersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemImageServersEntityRepository extends JpaRepository<SystemImageServersEntity, Integer>, SystemImageServersEntityRepositoryCustom {
}
