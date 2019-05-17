package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.photostudio.PhotoUnitRepositoryCustom;
import net.fashiongo.webadmin.model.photostudio.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
public class PhotoUnitRepositoryCustomImpl implements PhotoUnitRepositoryCustom {

	@PersistenceContext(unitName = "photostudioEntityManager")
	private EntityManager photostudioEntityManager;

	@Override
	public List<PhotoUnit> findAllCurrentEffectiveUnit(Integer categoryId, Integer packageId, boolean isFullModelShot) {
		QPhotoUnit photoUnit = QPhotoUnit.photoUnit;

		LocalDateTime now = LocalDateTime.now();
		BooleanExpression condition = photoUnit._fromEffectiveDate.before(now)
				.and(photoUnit._toEffectiveDate.after(now)
						.or(photoUnit._toEffectiveDate.isNull()))
				.and(photoUnit.photoshootType.eq(isFullModelShot ? 1 : 2));

		// OLD
		if (Arrays.asList(1, 2, 3, 4).contains(categoryId)) {
			condition = condition.and(photoUnit.packageId.isNull());
		} else {
			condition = condition.and(photoUnit.packageId.eq(packageId));
		}

		JPAQuery<PhotoUnit> query = new JPAQuery<>(photostudioEntityManager)
				.select(photoUnit)
				.from(photoUnit)
				.where(condition);

		return query.fetch();
	}

	@Override
	public List<PhotoUnit> findAllCurrentEffectiveUnit(LocalDateTime now) {
		QPhotoUnit photoUnit = QPhotoUnit.photoUnit;
		QPhotoPackage photoPackage = QPhotoPackage.photoPackage;
		QPhotoCategory photoCategory = QPhotoCategory.photoCategory;

		JPAQuery<PhotoUnit> query = new JPAQuery<>(photostudioEntityManager)
				.select(photoUnit)
				.from(photoUnit)
				.innerJoin(photoUnit.photoPackage, photoPackage).fetchJoin()
				.innerJoin(photoPackage.photoCategory, photoCategory).fetchJoin()
				.where(photoUnit.packageId.isNotNull()
						.and(photoUnit._fromEffectiveDate.before(now))
						.and(photoUnit._toEffectiveDate.after(now)
								.or(photoUnit._toEffectiveDate.isNull())));

		return query.fetch();
	}

	@Override
	public List<PhotoUnit> findAllToBeEffectiveUnit(LocalDateTime now) {
		QPhotoUnit photoUnit = QPhotoUnit.photoUnit;
		QPhotoPackage photoPackage = QPhotoPackage.photoPackage;
		QPhotoCategory photoCategory = QPhotoCategory.photoCategory;

		JPAQuery<PhotoUnit> query = new JPAQuery<>(photostudioEntityManager)
				.select(photoUnit)
				.from(photoUnit)
				.innerJoin(photoUnit.photoPackage, photoPackage).fetchJoin()
				.innerJoin(photoPackage.photoCategory, photoCategory).fetchJoin()
				.where(photoUnit.packageId.isNotNull()
						.and(photoUnit._fromEffectiveDate.after(now))
						.and(photoUnit._toEffectiveDate.isNull()));

		return query.fetch();
	}
}
