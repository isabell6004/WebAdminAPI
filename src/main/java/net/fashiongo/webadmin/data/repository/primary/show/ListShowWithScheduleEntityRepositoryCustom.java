package net.fashiongo.webadmin.data.repository.primary.show;

import net.fashiongo.webadmin.data.entity.primary.show.ListShowWithScheduleEntity;
import org.springframework.data.domain.Page;


public interface ListShowWithScheduleEntityRepositoryCustom {
	Page<ListShowWithScheduleEntity> getShowList(ListShowSelectParameter param);
}
