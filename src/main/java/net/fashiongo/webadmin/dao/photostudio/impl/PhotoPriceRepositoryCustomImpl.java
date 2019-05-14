package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.photostudio.PhotoPriceRepositoryCustom;
import net.fashiongo.webadmin.model.photostudio.PhotoPrice;
import net.fashiongo.webadmin.model.photostudio.QPhotoCategory;
import net.fashiongo.webadmin.model.photostudio.QPhotoPackage;
import net.fashiongo.webadmin.model.photostudio.QPhotoPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PhotoPriceRepositoryCustomImpl implements PhotoPriceRepositoryCustom {

    @PersistenceContext(unitName = "photostudioEntityManager")
    private EntityManager photostudioEntityManager;

    @Override
    public List<PhotoPrice> findAllCurrentEffectivePrice(LocalDateTime now) {
        QPhotoPrice photoPrice = QPhotoPrice.photoPrice;
        QPhotoPackage photoPackage = QPhotoPackage.photoPackage;
        QPhotoCategory photoCategory = QPhotoCategory.photoCategory;

        JPAQuery<PhotoPrice> query = new JPAQuery<>(photostudioEntityManager)
                .select(photoPrice)
                .from(photoPrice)
                .innerJoin(photoPrice.photoPackage, photoPackage).fetchJoin()
                .innerJoin(photoPackage.photoCategory, photoCategory).fetchJoin()
                .where(photoPrice.packageId.isNotNull()
                        .and(photoPrice._fromEffectiveDate.before(now))
                        .and(photoPrice._toEffectiveDate.after(now)
                                .or(photoPrice._toEffectiveDate.isNull())));

        return query.fetch();
    }

    @Override
    public List<PhotoPrice> findAllToBeEffectivePrice(LocalDateTime now) {
        QPhotoPrice photoPrice = QPhotoPrice.photoPrice;
        QPhotoPackage photoPackage = QPhotoPackage.photoPackage;
        QPhotoCategory photoCategory = QPhotoCategory.photoCategory;

        JPAQuery<PhotoPrice> query = new JPAQuery<>(photostudioEntityManager)
                .select(photoPrice)
                .from(photoPrice)
                .innerJoin(photoPrice.photoPackage, photoPackage).fetchJoin()
                .innerJoin(photoPackage.photoCategory, photoCategory).fetchJoin()
                .where(photoPrice.packageId.isNotNull()
                        .and(photoPrice._fromEffectiveDate.after(now))
                        .and(photoPrice._toEffectiveDate.isNull()));

        return query.fetch();
    }
}
