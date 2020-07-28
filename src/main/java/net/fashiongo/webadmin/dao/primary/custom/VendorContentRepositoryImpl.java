package net.fashiongo.webadmin.dao.primary.custom;

import static net.fashiongo.webadmin.model.primary.QVendorContent.vendorContent;
import static net.fashiongo.webadmin.model.primary.QVendorContentFile.vendorContentFile;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.model.primary.VendorContentFile;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.Expressions;

import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.SingleValueResult;
import net.fashiongo.webadmin.model.primary.Vendor;
import net.fashiongo.webadmin.model.primary.VendorContent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-13
 */
public class VendorContentRepositoryImpl extends QuerydslRepositorySupport implements VendorContentRepositoryCustom {

    public VendorContentRepositoryImpl() {
        super(Vendor.class);
    }
	
    @Override
    @PersistenceContext(unitName = "primaryEntityManager")
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
	public PagedResult<VendorContent> getVendorContents(Integer pagenum, Integer pagesize, String company,Integer contentfileid, LocalDateTime datefrom,
                                                        LocalDateTime dateto, Integer type, Integer filetype, Integer status) {


        BooleanBuilder where = new BooleanBuilder(vendorContent.isDeleted.eq(false));
        Optional.ofNullable(company)
                .ifPresent(d -> where.and(vendorContent.vendor.companyName.likeIgnoreCase(Expressions.asString("%").concat(d).concat("%"))));
        Optional.ofNullable(datefrom)
                .ifPresent(d -> where.and(vendorContent.requestedOn.goe(d)));
        Optional.ofNullable(dateto)
                .ifPresent(d -> where.and(vendorContent.requestedOn.loe(d)));
        Optional.ofNullable(type)
                .ifPresent(d -> where.and(vendorContent.targetTypeId.eq(d)));
        Optional.ofNullable(status)
                .ifPresent(d -> where.and(vendorContent.statusId.eq(d)));
        Optional.ofNullable(filetype)
                .ifPresent(d -> where.and(vendorContent.vendorContentId.in(
                        JPAExpressions.select(vendorContentFile.vendorContentId).from(vendorContentFile).where(vendorContentFile.fileType.eq(d))
                )));
        Optional.ofNullable(contentfileid)
                .ifPresent(d -> where.and(vendorContent.vendorContentId.in(
                        JPAExpressions.select(vendorContentFile.vendorContentId).from(vendorContentFile).where(vendorContentFile.vendorContentFileId.eq(d))
                )));

        QueryResults<VendorContent> results =
				from(vendorContent)
				.where(where)
				.offset(pagesize!=null && pagenum!=null ? pagesize*((long)pagenum-1) : 0)
				.limit(pagesize!=null ? pagesize : 100)
				.fetchResults();
		
		PagedResult<VendorContent> pagedResults = new PagedResult<>();
		SingleValueResult total = new SingleValueResult();
        total.setTotalCount((int)results.getTotal());
        pagedResults.setTotal(total);
        pagedResults.setRecords(results.getResults());
        return pagedResults;
    }
}