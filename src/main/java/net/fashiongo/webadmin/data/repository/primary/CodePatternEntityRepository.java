package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeFabricEntity;
import net.fashiongo.webadmin.data.entity.primary.CodePatternEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodePatternEntityRepository extends JpaRepository<CodePatternEntity,Integer>, CodePatternEntityRepositoryCustom {
}
