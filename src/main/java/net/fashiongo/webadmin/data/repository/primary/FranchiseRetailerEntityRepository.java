package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.franchise.FranchiseRetailerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRetailerEntityRepository extends JpaRepository<FranchiseRetailerEntity,Integer>, FranchiseRetailerEntityRepositoryCustom {

}
