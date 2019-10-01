package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeCountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeCountryEntityRepository extends JpaRepository<CodeCountryEntity,Integer> , CodeCountryEntityRepositoryCustom{
}
