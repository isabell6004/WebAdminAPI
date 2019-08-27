package net.fashiongo.webadmin.data.repository.primary.show;

import net.fashiongo.webadmin.data.entity.primary.show.ListShowWithScheduleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListShowWithScheduleEntityRepository extends CrudRepository<ListShowWithScheduleEntity, Integer>, ListShowWithScheduleEntityRepositoryCustom {
}
