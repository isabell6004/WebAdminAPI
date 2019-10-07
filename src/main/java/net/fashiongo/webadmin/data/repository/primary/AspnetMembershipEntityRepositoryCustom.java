package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.AspnetMembershipEntity;

import java.util.Optional;

public interface AspnetMembershipEntityRepositoryCustom {

	Optional<AspnetMembershipEntity> findByUserName(String userName);
}
