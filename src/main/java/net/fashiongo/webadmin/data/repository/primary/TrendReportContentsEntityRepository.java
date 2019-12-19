package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendReportContentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendReportContentsEntityRepository extends JpaRepository<TrendReportContentsEntity, Integer>, TrendReportContentsEntityRepositoryCustom {
}
