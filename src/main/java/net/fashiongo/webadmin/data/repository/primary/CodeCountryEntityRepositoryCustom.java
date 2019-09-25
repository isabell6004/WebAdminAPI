package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeCountryEntity;

import java.util.List;

public interface CodeCountryEntityRepositoryCustom {

	List<CodeCountryEntity> findAllByActive(boolean isActive);
}
