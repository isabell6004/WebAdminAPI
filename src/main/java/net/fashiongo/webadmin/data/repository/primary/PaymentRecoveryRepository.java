package net.fashiongo.webadmin.data.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fashiongo.webadmin.data.entity.primary.PaymentRecoveryEntity;

public interface PaymentRecoveryRepository extends JpaRepository<PaymentRecoveryEntity, Integer>, PaymentRecoveryRepositoryCustom {

}
