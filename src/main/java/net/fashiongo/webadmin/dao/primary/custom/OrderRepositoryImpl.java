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
    
    @Override
    public int getInvalidConsolidationOrderCount(Integer consolidationId) {
        JPAQuery<Integer> query = new JPAQuery<>(entityManager);
        QOrder order = QOrder.order;

        return query
                .select(
                        Projections.constructor(
                            Integer.class,
                                order.id.count()
                        )
                )
                .from(order)
                .where(
                        order.consolidationId.eq(consolidationId)
                        .and(order.orderStatusId.eq(5)
                        		.or(order.orderStatusId.eq(7))
                        		.or(order.isConsolidated.isFalse()))
                        		
                )
                .fetchFirst();
    }
    
}
