package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendReportMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendReportMapEntityRepository extends JpaRepository<TrendReportMapEntity, Integer>, TrendReportMapEntityRepositoryCustom {
}
