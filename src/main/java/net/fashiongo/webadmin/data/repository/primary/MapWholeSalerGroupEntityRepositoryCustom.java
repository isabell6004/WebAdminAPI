package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerGroupEntity;

import java.util.ArrayList;
import java.util.List;

public interface MapWholeSalerGroupEntityRepositoryCustom {
    List<MapWholeSalerGroupEntity> findAllByIds(ArrayList<Integer> wids);
}
