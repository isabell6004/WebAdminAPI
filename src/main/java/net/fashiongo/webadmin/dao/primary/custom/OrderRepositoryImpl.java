package net.fashiongo.webadmin.dao.primary.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QOrder;
import net.fashiongo.webadmin.model.pojo.consolidation.Dto.OrderConsolidationSummaryDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public OrderConsolidationSummaryDto getOrderConsolidationSummary(Integer consolidationId) {
        JPAQuery<OrderConsolidationSummaryDto> query = new JPAQuery<>(entityManager);
        QOrder order = QOrder.order;

        return query
                .select(
                        Projections.constructor(
                            OrderConsolidationSummaryDto.class,
                                order.id.count(),
                                order.totalAmount.sum(),
                                order.totalQty.sum()
                        )
                )
                .from(order)
                .where(
                        order.consolidationId.eq(consolidationId)
                        .and(order.isConsolidated.isTrue())
                )
                .fetchFirst();
    }
}
