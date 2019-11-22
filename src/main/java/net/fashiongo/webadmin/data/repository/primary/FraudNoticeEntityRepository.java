package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.FraudNoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudNoticeEntityRepository extends JpaRepository<FraudNoticeEntity,Integer>, FraudNoticeEntityRepositoryCustom {
}
