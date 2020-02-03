package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.OrderPaymentStatusEntity;
import net.fashiongo.webadmin.data.model.payment.OrderPayment;

import java.util.List;
import java.util.Optional;

public interface OrderPaymentStatusEntityRepositoryCustom {

	Optional<OrderPaymentStatusEntity> findFirst(int referenceId,int isOrder,int paymentStatusId);

	long deleteByReferenceIdAndIsOrder(int referenceId,int isOrder);

	Optional<OrderPaymentStatusEntity> findOneByReferenceIDAndIsOrder(int referenceId,int isOrder);

	List<OrderPayment> getOrderPayment(Integer creditCardID);
}
