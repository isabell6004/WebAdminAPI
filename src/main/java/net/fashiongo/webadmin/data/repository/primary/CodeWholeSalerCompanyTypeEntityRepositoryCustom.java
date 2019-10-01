package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeWholeSalerCompanyTypeEntity;

import java.util.List;

public interface CodeWholeSalerCompanyTypeEntityRepositoryCustom {

	List<CodeWholeSalerCompanyTypeEntity> findAllByActive(boolean isActive);
}
