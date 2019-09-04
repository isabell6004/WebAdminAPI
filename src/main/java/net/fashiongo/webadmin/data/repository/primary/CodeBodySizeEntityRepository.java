package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeBodySizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeBodySizeEntityRepository extends JpaRepository<CodeBodySizeEntity, Integer>, CodeBodySizeEntityRepositoryCustom {
}
