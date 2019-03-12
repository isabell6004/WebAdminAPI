package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoOrderDetailRepository extends CrudRepository<PhotoOrderDetail, Integer> {

    List<PhotoOrderDetail> findByOrderID(Integer orderID);
}
