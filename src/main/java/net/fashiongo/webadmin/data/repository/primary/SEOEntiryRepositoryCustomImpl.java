package net.fashiongo.webadmin.data.repository.primary;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;

import net.fashiongo.webadmin.data.entity.primary.QSEOEntity;
import net.fashiongo.webadmin.data.entity.primary.SEOEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.model.kmm.KmmListDetail;
import net.fashiongo.webadmin.data.model.sitemgmt.SEO;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;

public class SEOEntiryRepositoryCustomImpl implements SEOEntiryRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;
 
   
    @Override
    public Page<SEO> findAllBySeo(int pageNo, int pageSize) {
        long offset = (pageNo - 1) * pageSize;
        long limit = pageSize;
        OrderSpecifier orderSpecifier = null;
        
        JPASQLQuery<SEO> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
    	QSEOEntity siteseo = QSEOEntity.sEOEntity;
    	orderSpecifier = siteseo.siteSeoId.asc();
               
    	jpasqlQuery.select(
                Projections.constructor(SEO.class,
                    siteseo.siteSeoId
                              ,siteseo.pageName
                              ,siteseo.url
                              ,siteseo.title
                              ,siteseo.metaKeyword
                              ,siteseo.metaDescription
                              ,siteseo.isActive
                              ,siteseo.createdOn
                              ,siteseo.createdBy
                              ,siteseo.modifiedOn
                              ,siteseo.modifiedBy
                              ,SQLExpressions.rowNumber().over().orderBy(orderSpecifier).as("row"))
            )
                .from(siteseo)
                .where(siteseo.isDeleted.eq(false))
                .orderBy(orderSpecifier)
                .offset(offset)
                .limit(limit);

        QueryResults<SEO> seoQueryResult = jpasqlQuery.fetchResults();
        long total = seoQueryResult.getTotal();
        List<SEO> results = seoQueryResult.getResults();
        
        PageRequest pageRequest = PageRequest.of(pageNo-1, pageSize);
        return PageableExecutionUtils.getPage(results,pageRequest,()-> total);
    }       

    @Override
    public SEOEntity findOneByID(Integer siteSEOId) {
    	
    	QSEOEntity siteseo = QSEOEntity.sEOEntity;
    	
        JPAQuery<SEOEntity> query = new JPAQuery<>(entityManager);

        query.select(siteseo).from(siteseo).where(siteseo.siteSeoId.eq(siteSEOId));

        return query.fetchFirst();
    	
    }
    
    @Override
    public List<SEOEntity> findOneByIDAll(List<Integer> siteSEOIds) {
    	
    	QSEOEntity siteseo = QSEOEntity.sEOEntity;
    	
        JPAQuery<SEOEntity> query = new JPAQuery<>(entityManager);

        query.select(siteseo).from(siteseo).where(siteseo.siteSeoId.in(siteSEOIds));

        return query.fetch();
    	
    }    
}
