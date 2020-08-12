package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CodeVendorIndustryEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeVendorIndustryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CodeVendorIndustryEntityRepositoryCustomImpl implements CodeVendorIndustryEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<CodeVendorIndustryEntity> findAllCodeVendorIndustriesOrderById() {
        JPAQuery<CodeVendorIndustryEntity> query = new JPAQuery<>(entityManager);

        QCodeVendorIndustryEntity CVI = QCodeVendorIndustryEntity.codeVendorIndustryEntity;

        List<CodeVendorIndustryEntity> result = query.select(CVI).from(CVI).where(CVI.active.eq(true)).orderBy(CVI.vendorIndustryID.asc()).fetch();

        return result;
    }
}
