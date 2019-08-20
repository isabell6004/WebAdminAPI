package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeLengthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeLengthEntityRepository extends JpaRepository<CodeLengthEntity,Integer>, CodeLengthEntityRepositoryCustom {
}
