package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QSystemImageServersEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorSettingEntity;
import net.fashiongo.webadmin.data.model.statistics.ImageServerUrl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SystemImageServersEntityRepositoryCustomImpl implements SystemImageServersEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<ImageServerUrl> findImageServerURlGroupByImageServerID() {
        QSystemImageServersEntity SIS = QSystemImageServersEntity.systemImageServersEntity;
        QVendorEntity W = QVendorEntity.vendorEntity;
        QVendorSettingEntity VS = QVendorSettingEntity.vendorSettingEntity;

        JPAQuery<ImageServerUrl> query = new JPAQuery<>(entityManager);
        query.select(Projections.constructor(ImageServerUrl.class,
                SIS.imageServerID.as("imageserverid"),
                W.vendor_id.count().as("WholeSalerCount"),
                SIS.imageServerName.max().as("ImageServerName"),
                SIS.urlPath.max().as("UrlPath")))
                .from(SIS)
                .leftJoin(W).on(SIS.imageServerID.eq(7))
                .leftJoin(W.vendorSetting, VS)
                .where(SIS.active.eq(true).and(VS.statusCode.eq(3)))
                .groupBy(SIS.imageServerID)
                .orderBy(W.vendor_id.count().desc(),SIS.imageServerID.asc());

        return query.fetch();
    }
}
