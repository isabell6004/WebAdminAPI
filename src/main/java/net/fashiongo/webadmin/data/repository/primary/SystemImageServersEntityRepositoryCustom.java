package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.statistics.ImageServerUrl;

import java.util.List;

public interface SystemImageServersEntityRepositoryCustom {
    List<ImageServerUrl> findImageServerURlGroupByImageServerID();
}
