package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SecurityUserEntity;

import java.util.List;

public interface SecurityUserEntityRepositoryCustom {
    List<SecurityUserEntity> findAllActive();
}
