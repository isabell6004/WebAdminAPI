package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeLengthEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.LengthInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CodeLengthEntityRepositoryCustom {

	Page<CodeLengthEntity> findAllByLengthNameAndActiveOrderByLengthName(String lengthName,Boolean active,int pageNumber, int pageSize);

	List<LengthInfo> findAllOrderByLengthName();
}
