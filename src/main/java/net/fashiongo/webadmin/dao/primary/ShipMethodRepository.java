package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.data.entity.primary.ShipMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipMethodRepository extends JpaRepository<ShipMethod, Integer> {
    List<ShipMethod> findByActiveAndIdIn(boolean active, List<Integer> ids);
}
