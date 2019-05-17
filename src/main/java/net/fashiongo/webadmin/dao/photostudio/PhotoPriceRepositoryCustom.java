package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoPrice;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoPriceRepositoryCustom {
    List<PhotoPrice> findAllCurrentEffectivePrice(LocalDateTime now);

    List<PhotoPrice> findAllToBeEffectivePrice(LocalDateTime now);
}
