package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.XColorMasterEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.ColorListInfo;

import java.util.List;

public interface XColorMasterEntityRepositoryCustom {
    List<ColorListInfo> findAllColors();
}
