package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoImageRepositoryCustom;
import net.fashiongo.webadmin.model.photostudio.MapPhotoImage;
import net.fashiongo.webadmin.model.photostudio.QMapPhotoImage;
import net.fashiongo.webadmin.model.photostudio.QPhotoImage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MapPhotoImageRepositoryCustomImpl implements MapPhotoImageRepositoryCustom {

	@PersistenceContext(unitName = "photostudioEntityManager")
	private EntityManager photostudioEntityManager;

	@Override
	public List<MapPhotoImage> findModelImageByModelId(Integer modelId) {
		QMapPhotoImage mapPhotoImage = QMapPhotoImage.mapPhotoImage;
		QPhotoImage photoImage = QPhotoImage.photoImage;

		JPAQuery<MapPhotoImage> query = new JPAQuery<>(photostudioEntityManager)
				.select(mapPhotoImage)
				.from(mapPhotoImage)
				.join(mapPhotoImage.photoImage, photoImage).fetchJoin()
				.where(mapPhotoImage.mappingType.eq(3)
						.and(mapPhotoImage.referenceID.eq(modelId)))
				.orderBy(mapPhotoImage.listOrder.asc());

		return query.fetch();
	}

	@Override
	public List<MapPhotoImage> findModelImagesByModelIds(List<Integer> modelIds) {
		if(modelIds.size() == 0 || modelIds.get(0) == null) return new ArrayList<>();

		QMapPhotoImage mapPhotoImage = QMapPhotoImage.mapPhotoImage;
		QPhotoImage photoImage = QPhotoImage.photoImage;

		JPAQuery<MapPhotoImage> query = new JPAQuery<>(photostudioEntityManager)
				.select(mapPhotoImage)
				.from(mapPhotoImage)
				.leftJoin(mapPhotoImage.photoImage, photoImage).fetchJoin()
				.where(mapPhotoImage.mappingType.eq(3)
						.and(mapPhotoImage.referenceID.in(modelIds)));

		return query.fetch();
	}

	@Override
	public List<MapPhotoImage> findPriceImagesByPriceIds(List<Integer> priceIds) {
		QMapPhotoImage mapPhotoImage = QMapPhotoImage.mapPhotoImage;

		JPAQuery<MapPhotoImage> query = new JPAQuery<>(photostudioEntityManager)
				.select(mapPhotoImage)
				.from(mapPhotoImage)
				.where(mapPhotoImage.mappingType.eq(4)
						.and(mapPhotoImage.referenceID.in(priceIds)));

		return query.fetch();
	}
}
