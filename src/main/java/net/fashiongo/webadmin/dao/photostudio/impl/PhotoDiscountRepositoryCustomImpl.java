package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.photostudio.PhotoDiscountRepositoryCustom;
import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;
import net.fashiongo.webadmin.model.photostudio.QPhotoDiscount;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PhotoDiscountRepositoryCustomImpl implements PhotoDiscountRepositoryCustom {

    @PersistenceContext(unitName = "photostudioEntityManager")
    private EntityManager photostudioEntityManager;

    @Override
    public Optional<PhotoDiscount> findUsableDiscountByDiscountCode(String discountCode) {

        LocalDateTime now = LocalDateTime.now();

        PhotoDiscount result = new JPAQuery<>(photostudioEntityManager)
                .select(QPhotoDiscount.photoDiscount)
                .from(QPhotoDiscount.photoDiscount)
                .where(QPhotoDiscount.photoDiscount.discountCode.eq(discountCode)
                        .and(QPhotoDiscount.photoDiscount._fromDate.loe(now))
                        .and(QPhotoDiscount.photoDiscount._toDate.goe(now)))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
