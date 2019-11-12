package net.fashiongo.webadmin.dao.primary.custom;

import net.fashiongo.webadmin.model.pojo.consolidation.Dto.OrderConsolidationSummaryDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrderRepositoryCustom {
    OrderConsolidationSummaryDto getOrderConsolidationSummary(Integer consolidationId);
}
