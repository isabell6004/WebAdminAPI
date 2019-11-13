package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.GnbMenuBannerTypeEntity;

import java.util.List;
import java.util.Optional;

public interface GnbMenuBannerTypeEntityRepositoryCustom {
	List<GnbMenuBannerTypeEntity> getList();

	Optional<GnbMenuBannerTypeEntity> getOneFromId(int bannerTypeId);
}
