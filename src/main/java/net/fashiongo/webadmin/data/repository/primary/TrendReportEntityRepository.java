package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendReportEntityRepository extends JpaRepository<TrendReportEntity, Integer>, TrendReportEntityRepositoryCustom {
}
