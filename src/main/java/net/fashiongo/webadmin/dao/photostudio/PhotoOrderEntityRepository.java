package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoOrderEntityRepository extends JpaRepository<PhotoOrderEntity, Integer>, PhotoOrderEntityRepositoryCustom {
    PhotoOrderEntity findByPoNumber(String poNumber);
}
