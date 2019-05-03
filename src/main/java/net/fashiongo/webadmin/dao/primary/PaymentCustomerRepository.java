package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.model.primary.PaymentCustomer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentCustomerRepository extends CrudRepository<PaymentCustomer, Integer> {
    Optional<PaymentCustomer> findByRetailerId(int retailerId);
}
