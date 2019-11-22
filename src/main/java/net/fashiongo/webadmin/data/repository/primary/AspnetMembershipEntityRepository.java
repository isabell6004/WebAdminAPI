package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.AspnetMembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AspnetMembershipEntityRepository extends JpaRepository<AspnetMembershipEntity,String>, AspnetMembershipEntityRepositoryCustom {
}
