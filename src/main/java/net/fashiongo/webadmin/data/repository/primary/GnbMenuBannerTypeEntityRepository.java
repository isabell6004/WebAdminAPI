package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.GnbMenuBannerTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GnbMenuBannerTypeEntityRepository extends JpaRepository<GnbMenuBannerTypeEntity, Integer>, GnbMenuBannerTypeEntityRepositoryCustom {
}
