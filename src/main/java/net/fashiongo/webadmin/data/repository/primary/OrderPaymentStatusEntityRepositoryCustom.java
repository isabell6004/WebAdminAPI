package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.OrderPaymentStatusEntity;

import java.util.Optional;

public interface OrderPaymentStatusEntityRepositoryCustom {

	Optional<OrderPaymentStatusEntity> findFirst(int referenceId,int isOrder,int paymentStatusId);
}
