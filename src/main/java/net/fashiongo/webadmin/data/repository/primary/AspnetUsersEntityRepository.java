package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.AspnetUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AspnetUsersEntityRepository extends JpaRepository<AspnetUsersEntity, Integer>, AspnetUsersEntityRepositoryCustom {
}
