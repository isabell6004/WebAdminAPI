package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.PolicyAgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyAgreementEntityRepository extends JpaRepository<PolicyAgreementEntity,Integer> ,PolicyAgreementEntityRepositoryCustom {
}
