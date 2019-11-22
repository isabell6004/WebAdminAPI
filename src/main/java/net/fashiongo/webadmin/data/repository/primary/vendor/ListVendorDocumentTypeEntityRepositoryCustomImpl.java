package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.ListVendorDocumentTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.QListVendorDocumentTypeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ListVendorDocumentTypeEntityRepositoryCustomImpl implements ListVendorDocumentTypeEntityRepositoryCustom{
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<ListVendorDocumentTypeEntity> findAllListVendorDocumentTypeEntity() {
        JPAQuery<ListVendorDocumentTypeEntity> query = new JPAQuery<>(entityManager);
        QListVendorDocumentTypeEntity VDT = QListVendorDocumentTypeEntity.listVendorDocumentTypeEntity;

        List<ListVendorDocumentTypeEntity> result = query.select(VDT).from(VDT).orderBy(VDT.vendorDocumentTypeID.asc()).fetch();

        return result;
    }
}
