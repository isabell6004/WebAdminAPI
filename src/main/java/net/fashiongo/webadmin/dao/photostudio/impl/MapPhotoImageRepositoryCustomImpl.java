package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoImageRepositoryCustom;
import net.fashiongo.webadmin.model.photostudio.MapPhotoImage;
import net.fashiongo.webadmin.model.photostudio.QMapPhotoImage;
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
	public List<MapPhotoImage> findModelImagesByModelIds(List<Integer> modelIds) {
		if(modelIds.size() == 0 || modelIds.get(0) == null) return new ArrayList<>();

		QMapPhotoImage mapPhotoImage = QMapPhotoImage.mapPhotoImage;

		JPAQuery<MapPhotoImage> query = new JPAQuery<>(photostudioEntityManager)
				.select(mapPhotoImage)
				.from(mapPhotoImage)
				.where(mapPhotoImage.mappingType.eq(3)
						.and(mapPhotoImage.referenceID.in(modelIds)));

		return query.fetch();
	}
}
