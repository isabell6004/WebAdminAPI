package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodePatternEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.PatternInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CodePatternEntityRepositoryCustom {

	Page<CodePatternEntity> findAllByPattenNameAndActiveOrderByPattenName(String PattenName, Boolean active, int pageNumber, int pageSize);

	List<PatternInfo> findAllOrderByPatternName();
}
