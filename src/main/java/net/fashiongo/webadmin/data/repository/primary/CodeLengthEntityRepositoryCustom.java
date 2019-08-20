package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeLengthEntity;
import org.springframework.data.domain.Page;

public interface CodeLengthEntityRepositoryCustom {

	Page<CodeLengthEntity> findAllByLengthNameAndActiveOrderByLengthName(String lengthName,Boolean active,int pageNumber, int pageSize);
}
