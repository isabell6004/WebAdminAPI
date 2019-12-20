package net.fashiongo.webadmin.dao.primary.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQuery;

import net.fashiongo.webadmin.dao.primary.custom.DropOffCustomRepository;
import net.fashiongo.webadmin.model.primary.consolidation.ConsolidatedOrder;
import net.fashiongo.webadmin.model.primary.consolidation.QConsolidatedOrder;
import net.fashiongo.webadmin.model.primary.consolidation.QConsolidationOrders;

public class ConsolidationDropoffRepositoryImpl implements DropOffCustomRepository{
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<ConsolidatedOrder> getDropOffConsolidationOrder(String poNumber) {
		JPAQuery<ConsolidatedOrder> query = new JPAQuery<>(entityManager);
		QConsolidatedOrder consolidatedOrder = QConsolidatedOrder.consolidatedOrder;
		QConsolidationOrders consolidationOrder = QConsolidationOrders.consolidationOrders;
		
		return query.select(consolidatedOrder)
				.from(consolidatedOrder)
				.leftJoin(consolidatedOrder.consolidationOrder, consolidationOrder).fetchJoin()
				.where(consolidatedOrder.poNumber.eq(poNumber))
				.fetch();
		
	}

	@Override
	public List<ConsolidatedOrder> getDropOffConsolidationReceipt(Integer orderId) {
		JPAQuery<ConsolidatedOrder> query = new JPAQuery<>(entityManager);
		QConsolidatedOrder consolidatedOrder = QConsolidatedOrder.consolidatedOrder;
		QConsolidationOrders consolidationOrder = QConsolidationOrders.consolidationOrders;
		
		return query.select(consolidatedOrder)
				.from(consolidatedOrder)
				.leftJoin(consolidatedOrder.consolidationOrder, consolidationOrder).fetchJoin()
				.where(consolidatedOrder.orderId.eq(orderId))
				.fetch();
	}
}
