package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CustomerSocialMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSocialMediaEntityRepository extends JpaRepository<CustomerSocialMediaEntity, Integer>, CustomerSocialMediaEntityRepositoryCustom {
}
