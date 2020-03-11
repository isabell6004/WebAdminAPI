package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusList;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface PaymentTransactionEntityRepositoryCustom {
    Page<PaymentStatusList> getPaymentStatusList(int pageNum, int pageSize, Integer wholesalerID,
                                                 Integer paymentStatusID, LocalDateTime fromDate,
                                                 LocalDateTime toDate, String poNumber, Integer consolidationID,
                                                 String buyerName, Integer transactionType, Integer searchSuccess, String orderBy);
}
