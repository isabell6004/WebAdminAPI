package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PhotoOrderRepository extends JpaRepository<PhotoOrder, Integer>, PhotoOrderStatisticCustom, PhotoOrderRepositoryCustom {

    List<PhotoOrder> findByCartIDIn(Set<Integer> cartIds);

}
