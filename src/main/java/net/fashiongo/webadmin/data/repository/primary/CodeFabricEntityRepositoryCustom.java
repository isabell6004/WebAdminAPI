package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeFabricEntity;
import net.fashiongo.webadmin.data.entity.primary.CodeLengthEntity;
import org.springframework.data.domain.Page;

public interface CodeFabricEntityRepositoryCustom {

	Page<CodeFabricEntity> findAllByFabricNameAndActiveOrderByFabricName(String FabricName, Boolean active, int pageNumber, int pageSize);
}
