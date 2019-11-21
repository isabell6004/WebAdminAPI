package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.ListSocialMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListSocialMediaEntityRepository extends JpaRepository<ListSocialMediaEntity, Integer>, ListSocialMediaEntityRepositoryCustom {
}
