package net.fashiongo.webadmin.data.repository.primary.form;

import net.fashiongo.webadmin.data.entity.primary.FashionGoFormEntity;
import org.springframework.data.repository.CrudRepository;

public interface FashionGoFormEntityRepository extends CrudRepository<FashionGoFormEntity, Integer>, FashionGoFormEntityRepositoryCustom {
}
