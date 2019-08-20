package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeFabricEntity;
import net.fashiongo.webadmin.data.entity.primary.CodeStyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeFabricEntityRepository extends JpaRepository<CodeFabricEntity,Integer>, CodeFabricEntityRepositoryCustom {
}
