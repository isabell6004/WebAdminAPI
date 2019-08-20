package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeFabricEntity;
import net.fashiongo.webadmin.data.entity.primary.CodePatternEntity;
import org.springframework.data.domain.Page;

public interface CodePatternEntityRepositoryCustom {

	Page<CodePatternEntity> findAllByPattenNameAndActiveOrderByPattenName(String PattenName, Boolean active, int pageNumber, int pageSize);
}
