package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.LogEmailSentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEmailSentEntityRepository extends JpaRepository<LogEmailSentEntity,Integer>, LogEmailSentEntityRepositoryCustom {
}
