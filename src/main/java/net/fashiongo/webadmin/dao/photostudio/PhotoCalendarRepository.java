package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoCalendarEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhotoCalendarRepository extends CrudRepository<PhotoCalendarEntity, Integer>, PhotoCalendarRepositoryCustom {

}
