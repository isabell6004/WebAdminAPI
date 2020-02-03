package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeCreditCardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeCreditCardTypeEntityRepository extends JpaRepository<CodeCreditCardTypeEntity, Integer>, CodeCreditCardTypeEntityRepositoryCustom {
}
