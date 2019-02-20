package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoBannerClick;
import org.springframework.data.repository.CrudRepository;

public interface PhotoBannerClickRepository extends CrudRepository<PhotoBannerClick, Integer>, PhotoBannerClickStatisticCustom {

}
