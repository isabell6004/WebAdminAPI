package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.FranchiseMasterAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseMasterAccountEntityRepository extends JpaRepository<FranchiseMasterAccountEntity,Integer>, FranchiseMasterAccountEntityRepositoryCustom {
}
