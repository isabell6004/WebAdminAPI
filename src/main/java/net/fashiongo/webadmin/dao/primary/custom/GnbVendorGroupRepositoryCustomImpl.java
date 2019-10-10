package net.fashiongo.webadmin.dao.primary.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupEntity;
import net.fashiongo.webadmin.data.entity.primary.QGnbVendorGroupEntity;
import net.fashiongo.webadmin.data.entity.primary.QGnbVendorGroupMapEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GnbVendorGroupRepositoryCustomImpl implements GnbVendorGroupRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	private QGnbVendorGroupEntity vendorGroup = QGnbVendorGroupEntity.gnbVendorGroupEntity;
	private QGnbVendorGroupMapEntity vendorGroupMap = QGnbVendorGroupMapEntity.gnbVendorGroupMapEntity;

	@Override
	public List<Integer> findIdListByWholeSalerIdAndTitle(Integer wholeSalerId, String title) {
		return new JPAQuery<>(entityManager)
				.select(vendorGroup.vendorGroupId).distinct()
				.from(vendorGroup)
				.leftJoin(vendorGroup.vendorGroupMaps, vendorGroupMap)
				.where(where(wholeSalerId, title))
				.fetch();
	}

	private BooleanBuilder where(Integer wholeSalerId, String title) {
		BooleanBuilder query = new BooleanBuilder();

		if (wholeSalerId != null) {
			query.and(vendorGroupMap.mapId.vendorId.eq(wholeSalerId));
		}

		if (title != null) {
			query.and(vendorGroup.vendorGroupTitle.contains(title));
		}

		return query;
	}

	@Override
	public List<GnbVendorGroupEntity> findAllByIdListWithGnbVendorGroupMap(List<Integer> idList) {
		return new JPAQuery<>(entityManager)
				.select(vendorGroup).distinct()
				.from(vendorGroup)
				.leftJoin(vendorGroup.vendorGroupMaps, vendorGroupMap).fetchJoin()
				.where(vendorGroup.vendorGroupId.in(idList))
				.orderBy(vendorGroup.vendorGroupId.desc())
				.fetch();
	}
}
