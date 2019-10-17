package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardEntityRepository extends JpaRepository<CreditCardEntity,Integer>, CreditCardEntityRepositoryCustom {
}
