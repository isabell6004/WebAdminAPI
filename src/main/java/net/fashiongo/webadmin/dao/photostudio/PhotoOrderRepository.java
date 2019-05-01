package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PhotoOrderRepository extends JpaRepository<PhotoOrder, Integer>, PhotoOrderRepositoryCustom {

    List<PhotoOrder> findByCartIDIn(Set<Integer> cartIds);

}
