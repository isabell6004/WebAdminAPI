package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.LogEmailSentEntity;

import java.util.List;

public interface LogEmailSentEntityRepositoryCustom {

	List<LogEmailSentEntity> findAllWithListEmailTypeByRetailerId(Integer retailerId);
}
