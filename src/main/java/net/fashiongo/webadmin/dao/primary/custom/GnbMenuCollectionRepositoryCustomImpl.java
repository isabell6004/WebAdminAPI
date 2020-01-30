package net.fashiongo.webadmin.dao.primary.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.GnbMenuCollectionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GnbMenuCollectionRepositoryCustomImpl implements GnbMenuCollectionRepositoryCustom {

//	@PersistenceContext(unitName = "primaryEntityManager")
//	private EntityManager entityManager;
//
//	private QGnbMenuCollectionEntity menuCollection = QGnbMenuCollectionEntity.gnbMenuCollectionEntity;
//	
//	@Override
//	public List<GnbMenuCollectionEntity> findAll() {
//		return new JPAQuery<>(entityManager)
//				.select(menuCollection)
//				.from(menuCollection)	
//				.orderBy(menuCollection.sortNo.asc())
//				.fetch();
//	}	
	
}
