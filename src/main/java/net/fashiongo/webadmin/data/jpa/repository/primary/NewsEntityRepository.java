package net.fashiongo.webadmin.data.jpa.repository.primary;

import net.fashiongo.webadmin.data.jpa.entity.primary.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsEntityRepository extends JpaRepository<NewsEntity, Integer>, NewsEntityRepositoryCustom {
}
