package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.ListCommunicationReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListCommunicationReasonEntityRepository extends JpaRepository<ListCommunicationReasonEntity,Integer>, ListCommunicationReasonEntityRepositoryCustom {
}
