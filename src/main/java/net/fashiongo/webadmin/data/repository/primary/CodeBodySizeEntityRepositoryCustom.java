package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeBodySizeEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.BodySizeInfo;

import java.util.List;

public interface CodeBodySizeEntityRepositoryCustom {
    List<BodySizeInfo> findAllWhereActiveTrue();
}
