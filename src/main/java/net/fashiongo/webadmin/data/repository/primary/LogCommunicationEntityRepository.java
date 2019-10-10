package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.LogCommunicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogCommunicationEntityRepository extends JpaRepository<LogCommunicationEntity,Integer>, LogCommunicationEntityRepositoryCustom {
}
