package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.MergeOrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MergeOrdersEntityRepository extends JpaRepository<MergeOrdersEntity,Integer>, MergeOrdersEntityRepositoryCustom{
}
