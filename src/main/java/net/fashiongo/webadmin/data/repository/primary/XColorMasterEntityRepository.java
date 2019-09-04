package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.XColorMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XColorMasterEntityRepository extends JpaRepository<XColorMasterEntity, Integer>, XColorMasterEntityRepositoryCustom {
}
