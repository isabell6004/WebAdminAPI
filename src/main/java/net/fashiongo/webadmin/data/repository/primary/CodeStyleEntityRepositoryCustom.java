package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeStyleEntity;
import org.springframework.data.domain.Page;

public interface CodeStyleEntityRepositoryCustom {

	Page<CodeStyleEntity> findAllByStyleNameAndActiveOrderByStyleName(String styleName, Boolean active, int pageNumber, int pageSize);
}
