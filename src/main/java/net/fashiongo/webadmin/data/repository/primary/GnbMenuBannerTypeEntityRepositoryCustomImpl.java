package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.GnbMenuBannerTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.QGnbMenuBannerEntity;
import net.fashiongo.webadmin.data.entity.primary.QGnbMenuBannerTypeEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class GnbMenuBannerTypeEntityRepositoryCustomImpl implements GnbMenuBannerTypeEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public List<GnbMenuBannerTypeEntity> getList() {
		QGnbMenuBannerTypeEntity qBannerType = QGnbMenuBannerTypeEntity.gnbMenuBannerTypeEntity;
		QGnbMenuBannerEntity qBanner = QGnbMenuBannerEntity.gnbMenuBannerEntity;

		return new JPAQuery<>(entityManager)
				.select(qBannerType)
				.from(qBannerType)
				.leftJoin(qBannerType.banners, qBanner).fetchJoin()
				.fetch();
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public Optional<GnbMenuBannerTypeEntity> getOneFromId(int bannerTypeId) {
		QGnbMenuBannerTypeEntity qBannerType = QGnbMenuBannerTypeEntity.gnbMenuBannerTypeEntity;
		QGnbMenuBannerEntity qBanner = QGnbMenuBannerEntity.gnbMenuBannerEntity;

		GnbMenuBannerTypeEntity entity = new JPAQuery<>(entityManager)
				.select(qBannerType)
				.from(qBannerType)
				.leftJoin(qBannerType.banners, qBanner).fetchJoin()
				.where(qBannerType.menuBannerTypeId.eq(bannerTypeId))
				.fetchFirst();
		return Optional.ofNullable(entity);
	}
}
