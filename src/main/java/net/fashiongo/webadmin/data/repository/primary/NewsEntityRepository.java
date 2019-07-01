package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsEntityRepository extends JpaRepository<NewsEntity, Integer>, NewsEntityRepositoryCustom {
}
