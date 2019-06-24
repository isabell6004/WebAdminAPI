package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.dao.primary.custom.NewsEntityRepositoryCustom;
import net.fashiongo.webadmin.model.primary.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsEntityRepository extends JpaRepository<NewsEntity, Integer>, NewsEntityRepositoryCustom {
}
