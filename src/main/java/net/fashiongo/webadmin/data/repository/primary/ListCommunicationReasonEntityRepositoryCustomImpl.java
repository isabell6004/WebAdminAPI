package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.ListCommunicationReasonEntity;
import net.fashiongo.webadmin.data.entity.primary.QListCommunicationReasonEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ListCommunicationReasonEntityRepositoryCustomImpl implements ListCommunicationReasonEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<ListCommunicationReasonEntity> findAllByActive(boolean isActive) {
		QListCommunicationReasonEntity L = new QListCommunicationReasonEntity("L");
		JPAQuery<ListCommunicationReasonEntity> jpaQuery = new JPAQuery<>(entityManager);

		jpaQuery.select(L)
				.from(L)
				.where(
						L.active.eq(isActive)
				);

		return jpaQuery.fetch();
	}
}
