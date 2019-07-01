package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.AdPageSpotEntity;

import java.util.List;

public interface AdPageSpotEntityRepositoryCustom {
    List<AdPageSpotEntity> findAllOrderByPageIDAscAndSpotIDAsc();
}
