package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeFabricEntity;
import net.fashiongo.webadmin.data.entity.primary.CodeLengthEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.FabricInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CodeFabricEntityRepositoryCustom {

	Page<CodeFabricEntity> findAllByFabricNameAndActiveOrderByFabricName(String FabricName, Boolean active, int pageNumber, int pageSize);

	List<FabricInfo> findAllOrderByFabricName();
}
