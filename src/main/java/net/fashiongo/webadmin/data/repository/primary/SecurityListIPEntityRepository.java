package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SecurityListIPEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityListIPEntityRepository extends JpaRepository<SecurityListIPEntity, Integer>, SecurityListIPEntityRepositoryCustom {
}
