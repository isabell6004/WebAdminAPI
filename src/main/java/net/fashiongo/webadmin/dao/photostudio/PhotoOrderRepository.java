package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.OrderDetailDailyReport;
import net.fashiongo.webadmin.model.photostudio.PhotoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PhotoOrderRepository extends CrudRepository<PhotoOrder, Integer>, PhotoOrderStatisticCustom {

    List<PhotoOrder> findByCartIDIn(Set<Integer> cartIds);

}
