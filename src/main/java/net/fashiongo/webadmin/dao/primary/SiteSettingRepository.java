package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.data.entity.primary.SiteSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteSettingRepository extends JpaRepository<SiteSettingEntity, Integer> {
}
