package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SecurityUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityUserEntityRepository extends JpaRepository<SecurityUserEntity,Integer> {
}
