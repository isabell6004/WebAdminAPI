package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemEntityRepository extends JpaRepository<CartItemEntity,Integer>, CartItemEntityRepositoryCustom {
}
