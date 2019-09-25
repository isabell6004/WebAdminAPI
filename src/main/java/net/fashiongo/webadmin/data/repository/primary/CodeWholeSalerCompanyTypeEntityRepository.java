package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeWholeSalerCompanyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeWholeSalerCompanyTypeEntityRepository extends JpaRepository<CodeWholeSalerCompanyTypeEntity,Integer>, CodeWholeSalerCompanyTypeEntityRepositoryCustom {
}
