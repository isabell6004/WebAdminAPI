package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeStyleEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.StyleInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CodeStyleEntityRepositoryCustom {

	Page<CodeStyleEntity> findAllByStyleNameAndActiveOrderByStyleName(String styleName, Boolean active, int pageNumber, int pageSize);

	List<StyleInfo> findAllOrderByStyleName();
}
