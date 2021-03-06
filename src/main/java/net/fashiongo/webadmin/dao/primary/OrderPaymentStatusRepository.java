package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.OrderPaymentStatus;

public interface OrderPaymentStatusRepository extends CrudRepository<OrderPaymentStatus, Integer> {
	OrderPaymentStatus findOneByOrderPaymentStatusID(Integer orderPaymentStatusID);
	OrderPaymentStatus findOneByReferenceIDAndIsOrder(Integer referenceID, Integer isOrder);
	OrderPaymentStatus findOneByReferenceIDAndIsOrderOrderByOrderPaymentStatusIDDesc(Integer referenceID, Integer isOrder);
}
